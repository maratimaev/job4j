package ru.job4j.xml;

import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 05.11.2018
 */
public class StoreSQLTest {
    @Test
    public void checkConnection() {
        StoreSQL sql = new StoreSQL("jdbc:sqlite:xml.s3db");
        assertThat(sql.connect(), is(true));
        sql.close();
    }

    @Test
    public void checkTableExists() {
        StoreSQL sql = new StoreSQL("jdbc:sqlite:xml.s3db");
        sql.connect();
        assertThat(sql.tableExists("entry"), is(true));
        sql.close();
    }

    @Test
    public void checkGenerate() {
        StoreSQL sql = new StoreSQL("jdbc:sqlite:xml.s3db");
        sql.generate(200);
        sql.connect();

        Connection conn = sql.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM entry");
            while (rs.next()) {
                int count = rs.getInt(1);
                assertThat(count, is(200));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Test
    public void whenUpdateDBThenGetThemFromDB() {
        ArrayList<String> param = new ArrayList<>();
        StoreSQL sql = new StoreSQL("jdbc:sqlite:xml.s3db");
        sql.connect();
        param.add("987654321");
        sql.updateDB("insert into entry (field) values (?)", param);
        try {
            Statement st = sql.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT field FROM entry WHERE field = '987654321'");
            while (rs.next()) {
                int i = rs.getInt("field");
                assertThat(rs.getInt("field"), is(987654321));
            }
            sql.updateDB("DELETE FROM entry WHERE field = '987654321'", new ArrayList<>());
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        sql.close();
    }
}
