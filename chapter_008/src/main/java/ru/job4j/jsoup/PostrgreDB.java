package ru.job4j.jsoup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 11.11.2018
 */
public class PostrgreDB {
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

    private boolean tableNotExists() {
        boolean result = true;
        try {
            DatabaseMetaData dbmd = this.connection.getMetaData();
            ResultSet rsTables = dbmd.getColumns(null, null, "messages", "%");
            if (!rsTables.next()) {
                LOGGER.warn("Table 'messages' not found");
            } else {
                LOGGER.info("Table 'messages' founded");
                result = false;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

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

    public boolean add(ArrayList<SQLRU> sqlruList) {
        PreparedStatement ps = null;
        boolean success = true;
        try {
            try {
                this.connection.setAutoCommit(false);
                ps = connection.prepareStatement("INSERT INTO messages (message, messageLink, memberName, "
                        + "answerCount, viewsCount, body, date) VALUES(?, ?, ?, ?, ?, ?, ?) ON CONFLICT DO NOTHING");
                int i = 0;
                int addedRows = 0;
                for (SQLRU msg: sqlruList) {
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
                        for (int j: count) {
                            addedRows += j;
                        }
                    }
                }
                int[] count = ps.executeBatch();
                for (int j: count) {
                    addedRows += j;
                }
                LOGGER.info(String.format("Messsages %s. Added to DB %s unique rows", sqlruList.size(), addedRows));
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                success = false;
            } finally {
                this.close(ps);
                if (success) {
                    connection.commit();
                } else {
                    connection.rollback();
                }
                this.close();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return success;
    }

    private ArrayList<SQLRU> query(String sql, ArrayList<String> parameters) {
        ArrayList<SQLRU> result = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = this.connection.prepareStatement(sql);
            int i = 0;
            for (String parameter : parameters) {
                ps.setObject(++i, parameter);
            }
            rs = ps.executeQuery();
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
                //LOGGER.info(String.format("Get item %s, %s, %s", item.getName(), item.getDescription(), item.getId()));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            close(rs);
            close(ps);
        }
        return result;
    }

    public SQLRU getLast() {
        SQLRU result = new SQLRU();
        ArrayList<SQLRU> last = query("SELECT message, messageLink, memberName, answerCount, viewsCount, body, date FROM messages WHERE message NOT LIKE 'Важно: %' ORDER BY date DESC LIMIT 1", new ArrayList<>());
        if (!last.isEmpty()) {
            result = last.get(0);
        }
        return result;
    }

    public ArrayList<SQLRU> getAll() {
        return query("SELECT message, messageLink, memberName, answerCount, viewsCount, body, date FROM messages", new ArrayList<>());
    }

    private void close(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

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
