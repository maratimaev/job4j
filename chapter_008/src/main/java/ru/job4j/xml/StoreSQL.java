package ru.job4j.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 05.11.2018
 */
public class StoreSQL {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreSQL.class.getName());
    Connection conn = null;
    Statement st = null;


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
                    ps = conn.prepareStatement("INSERT OR REPLACE INTO entry (field) VALUES(?)");

                    for (int i = 0; i < n; i++) {
                        ps.setInt(1, i);
                        ps.addBatch();
                        if (i % 1000 == 0) {
                            ps.executeBatch();
                        }
                        ps.executeBatch();
                        conn.commit();
                    }

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

        public boolean connect() {
            if (this.conn == null) {
                try {
                    Class.forName("org.sqlite.JDBC");
                    this.conn = DriverManager.getConnection("jdbc:sqlite:xml.s3db");
                } catch (Exception e) {
                    LOGGER.error(e.getMessage(), e);
                }
                LOGGER.info("Opened database successfully");
            }
            return true;
        }

        private void updateDB(String sql, List<String> parameters) {
            PreparedStatement ps = null;
            try {
                ps = this.conn.prepareStatement(sql);
                int i = 0;
                for (String parameter : parameters) {
                    ps.setObject(++i, parameter);
                }
                ps.executeUpdate();
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            } finally {
                close(ps);
            }
        }

        public boolean tableExists(String tableName) {
            boolean result = false;
            if (this.conn != null) {
                try{
                    DatabaseMetaData md = conn.getMetaData();
                    ResultSet rs = md.getColumns(null, null, tableName, "%");
                    result = rs.next();
                }catch(SQLException e){
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

    }
