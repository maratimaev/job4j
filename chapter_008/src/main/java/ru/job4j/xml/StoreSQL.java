package ru.job4j.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** Получение данных из БД
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 05.11.2018
 */
public class StoreSQL implements AutoCloseable {
    private static final Logger LOGGER = LoggerFactory.getLogger(StoreSQL.class.getName());
    /**
     * Экземпляр соединения с БД
     */
    private Connection conn = null;

    /**
     * Поле с параметрами подключения к БД
     */
    private Config config;

    public StoreSQL(Config config) {
        this.config = config;
    }

    /** Генерация n строк в БД
     * @param n типа int
     */
    public void generate(int n) {
        if (this.connect()) {
            if (this.tableNotExists("entry")) {
                this.updateDB("CREATE TABLE entry (field INTEGER)", new ArrayList<>());
            } else {
                this.updateDB("DELETE FROM entry", new ArrayList<>());
            }
            boolean success = true;
            try {
                this.conn.setAutoCommit(false);
                try (PreparedStatement ps = this.conn.prepareStatement("INSERT INTO entry (field) VALUES(?)")) {
                    for (int i = 0; i <= n; i++) {
                        ps.setInt(1, i);
                        ps.addBatch();
                        if (i % 1000 == 0) {
                            int[] count = ps.executeBatch();
                        }
                    }
                    ps.executeBatch();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
                success = false;
            } finally {
                try {
                    if (success) {
                        conn.commit();
                    } else {
                        conn.rollback();
                    }
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
    }

    /** Установка соединения с БД this.config
     * @return типа boolean
     */
    public boolean connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.conn = DriverManager.getConnection(this.config.get("sqliteDB"));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER.info("Opened database successfully");
        return true;
    }

    /** Выполнение запроса на изменение данных в БД
     * @param sql запрос типа String
     * @param parameters для PrepareStatement
     */
    public <T> int updateDB(String sql, List<T> parameters) {
        int numRowsUpdated = 0;
        try (PreparedStatement ps = this.conn.prepareStatement(sql)) {
            int i = 0;
            for (T parameter : parameters) {
                ps.setObject(++i, parameter);
            }
            numRowsUpdated = ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return numRowsUpdated;
    }

    /** Получение столбца из БД
     * @param sql запрос
     * @param parameters для prepareStatement
     * @param column имя столбца
     * @param <T> типа данных в столбце
     * @param <E> тип в prepareStatement
     * @return список данных столбца
     */
    public <T, E> ArrayList<T> getColumn(String sql, List<E> parameters, String column) {
        ArrayList<T> results = new ArrayList<>();
        try (PreparedStatement ps = this.conn.prepareStatement(sql)) {
            int i = 0;
            for (E parameter : parameters) {
                ps.setObject(++i, parameter);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    T value = (T) rs.getObject(column);
                    results.add(value);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return results;
    }

    /** Проверка существования таблицы в БД
     * @param tableName имя таблицы
     * @return типа boolean
     */
    public boolean tableNotExists(String tableName) {
        boolean result = false;
        if (this.conn != null) {
            try {
                DatabaseMetaData md = conn.getMetaData();
                try (ResultSet rs = md.getColumns(null, null, tableName, "%")) {
                    result = rs.next();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } else {
            LOGGER.error("Can't check table existance, DB not connected");
        }
        return !result;
    }

    /**
     * Закрытие соединения с БД
     */
    @Override
    public void close() {
        try {
            if (this.conn != null) {
                this.conn.close();
                LOGGER.info("DB disconnected");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
