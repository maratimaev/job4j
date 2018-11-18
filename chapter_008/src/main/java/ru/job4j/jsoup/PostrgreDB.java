package ru.job4j.jsoup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

/** Класс для работы с БД
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 11.11.2018
 */
public class PostrgreDB implements AutoCloseable {
    private static final Logger LOGGER = LoggerFactory.getLogger(PostrgreDB.class.getName());
    /**
     * Экземпляр соединения с БД
     */
    private Connection connection;

    /** Установка соединения с БД
     * @return результат boolean
     */
    public boolean connect() {
        LOGGER.info("Connect to DB");
        try (InputStream in = PostrgreDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("parserURL"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER.info("Connected OK " + this.connection);
        if (tableNotExists()) {
            createEmptyTable();
        }
        return this.connection != null;
    }

    /** Проверка на существование таблицы
     * @return типа boolean
     */
    public boolean tableNotExists() {
        boolean result = true;
        if (this.connection != null) {
            try {
                DatabaseMetaData dbmd = this.connection.getMetaData();
                try (ResultSet rsTables = dbmd.getColumns(null, null, "messages", "%")) {
                    result = rsTables.next();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } else {
            LOGGER.error("Can't check table existance, DB not connected");
        }
        LOGGER.warn(String.format("Table 'messages' founded: %s", result));
        return !result;
    }


    /**
     * Создание пустой таблицы
     */
    private void createEmptyTable() {
        try (Statement st = this.connection.createStatement()) {
            st.execute("CREATE TABLE messages ("
                    + "id serial PRIMARY KEY, "
                    + "message VARCHAR(2000), "
                    + "messageLink VARCHAR(200), "
                    + "memberName VARCHAR(100), "
                    + "answerCount VARCHAR(100), "
                    + "viewsCount VARCHAR(100), "
                    + "body TEXT, "
                    + "date TIMESTAMP, "
                    + "UNIQUE(message), "
                    + "UNIQUE(body))"
            );
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER.info("Empty table 'message' created");
    }

    /** Добавление списка строк в таблицу
     * @param sqlruList список строк
     * @return типа boolean
     */
    public boolean add(ArrayList<SQLRU> sqlruList) {
        boolean success = true;
        try {
            this.connection.setAutoCommit(false);
            int i = 0;
            int addedRows = 0;
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO messages (message, messageLink, memberName, "
                    + "answerCount, viewsCount, body, date) VALUES(?, ?, ?, ?, ?, ?, ?) ON CONFLICT DO NOTHING")) {
                for (SQLRU msg : sqlruList) {
                    ps.setString(1, msg.getMessage());
                    ps.setString(2, msg.getMessageLink());
                    ps.setString(3, msg.getMemberName());
                    ps.setString(4, msg.getAnswerCount());
                    ps.setString(5, msg.getViewsCount());
                    ps.setString(6, msg.getBody());
                    ps.setTimestamp(7, new Timestamp(msg.getDate().getTime()));
                    ps.addBatch();

                    if (i++ % 1000 == 0) {
                        int[] count = ps.executeBatch();
                        for (int j : count) {
                            addedRows += j;
                        }
                    }
                }
                int[] count = ps.executeBatch();
                for (int j : count) {
                    addedRows += j;
                }
            }
            LOGGER.info(String.format("Messsages %s. Added to DB %s unique rows", sqlruList.size(), addedRows));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            success = false;
        } finally {
            try {
                if (success) {
                    connection.commit();
                } else {
                    connection.rollback();
                }
                this.connection.setAutoCommit(true);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
            return success;
        }
    }

    /** Удаление строки из таблицы
     * @param sqlru строка
     * @return типа boolean
     */
    public boolean delete(SQLRU sqlru) {
        boolean result = false;
        String sql = "DELETE FROM messages WHERE message = ? AND messageLink = ? AND memberName = ?";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setString(1, sqlru.getMessage());
            ps.setString(2, sqlru.getMessageLink());
            ps.setString(3, sqlru.getMemberName());
            int count = ps.executeUpdate();
            if (count > 0) {
                result = true;
                LOGGER.info(String.format("Messsage %s deleted", sqlru.getMessage()));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    /** Запрос строк из таблицы
     * @param sql тело запроса
     * @param parameters параметры для Prepared
     * @return список строк
     */
    public ArrayList<SQLRU> query(String sql, ArrayList<String> parameters) {
        ArrayList<SQLRU> result = new ArrayList<>();
        int i = 0;
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            for (String parameter : parameters) {
                ps.setObject(++i, parameter);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SQLRU msg = new SQLRU();
                    msg.setMessage(rs.getString("message"));
                    msg.setMessageLink(rs.getString("messageLink"));
                    msg.setMemberName(rs.getString("memberName"));
                    msg.setAnswerCount(rs.getString("answerCount"));
                    msg.setViewsCount(rs.getString("viewsCount"));
                    msg.setBody(rs.getString("body"));
                    msg.setDate(new SimpleDateFormat("y-M-d H:m:s").parse(rs.getString("date")));
                    result.add(msg);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    /** Получение последней по дате строки из таблицы
     * @return объект SQLRU
     */
    public SQLRU getLast() {
        SQLRU result = new SQLRU();
        ArrayList<SQLRU> last = query("SELECT message, messageLink, memberName, answerCount, viewsCount, body, date FROM messages WHERE message NOT LIKE 'Важно: %' ORDER BY date DESC LIMIT 1", new ArrayList<>());
        if (!last.isEmpty()) {
            result = last.get(0);
        }
        return result;
    }

    /** Получение всех строк из таблицы
     * @return список объектов SQLRU
     */
    public ArrayList<SQLRU> getAll() {
        return query("SELECT message, messageLink, memberName, answerCount, viewsCount, body, date FROM messages", new ArrayList<>());
    }

    /**
     * Закрытие соединения с БД через autclosable
     */
    @Override
    public void close() {
        try {
            if (this.connection != null) {
                this.connection.close();
                LOGGER.info("DB disconnected");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
