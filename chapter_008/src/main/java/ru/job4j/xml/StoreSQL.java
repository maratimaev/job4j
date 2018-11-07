package ru.job4j.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 05.11.2018
 */
public class StoreSQL {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreSQL.class.getName());
    private Connection conn = null;

    /**
     * Поле с параметрами подключения к БД
     */
    private String config;

    public StoreSQL(String config) {
        this.config = config;
    }

    /** Генерация n строк в БД
     * @param n типа int
     */
    public void generate(int n) {
        if (this.connect()) {
            if (!this.tableExists("entry")) {
                this.updateDB("CREATE TABLE entry (field INTEGER)", new ArrayList<>());
            } else {
                this.updateDB("DELETE FROM entry", new ArrayList<>());
            }
            PreparedStatement ps = null;
            boolean success = true;
            try {
                try {
                    this.conn.setAutoCommit(false);
                    ps = conn.prepareStatement("INSERT INTO entry (field) VALUES(?)");
                    for (int i = 0; i < n; i++) {
                        ps.setInt(1, i);
                        ps.addBatch();
                        if (i % 1000 == 0) {
                            int[] count = ps.executeBatch();
                        }
                    }
                    ps.executeBatch();
                } catch (Exception e) {
                    LOGGER.error(e.getMessage(), e);
                    success = false;
                } finally {
                    this.close(ps);
                    if (success) {
                        conn.commit();
                    } else {
                        conn.rollback();
                    }
                    this.close();
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    /** Установка соединения с БД this.config
     * @return типа boolean
     */
    public boolean connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.conn = DriverManager.getConnection(this.config);
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
        PreparedStatement ps = null;
        int numRowsUpdated = 0;
        try {
            ps = this.conn.prepareStatement(sql);
            int i = 0;
            for (T parameter : parameters) {
                ps.setObject(++i, parameter);
            }
            numRowsUpdated = ps.executeUpdate();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            close(ps);
        }
        return numRowsUpdated;
    }

    public ArrayList<Integer> getColumn(String sql, ArrayList<String> parameters, String column) {
        ArrayList<Integer> results = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = this.conn.prepareStatement(sql);
            int i = 0;
            for (String parameter : parameters) {
                ps.setString(++i, parameter);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                int value = rs.getInt(column);
                results.add(value);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            close(rs);
            close(ps);
        }
        return results;
    }

    /** Проверка существования таблицы в БД
     * @param tableName имя таблицы
     * @return типа boolean
     */
    public boolean tableExists(String tableName) {
        boolean result = false;
        if (this.conn != null) {
            try {
                DatabaseMetaData md = conn.getMetaData();
                ResultSet rs = md.getColumns(null, null, tableName, "%");
                result = rs.next();
                close(rs);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } else {
            LOGGER.error("Can't check table existance, DB not connected");
        }
        return result;
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

    /**
     * Закрытие соединения с БД
     */
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

    public Connection getConnection() {
        return this.conn;
    }
}
