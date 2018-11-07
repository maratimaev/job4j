package ru.job4j.xml;

import org.junit.Test;
import java.util.ArrayList;
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
        ArrayList<Integer> list;
        StoreSQL sql = new StoreSQL("jdbc:sqlite:xml.s3db");
        sql.generate(200);
        sql.connect();
        list = sql.getColumn("SELECT field FROM entry", new ArrayList<>(), "field");
        assertThat(list.size(), is(200));
    }

    @Test
    public void whenUpdateDBThenGetThemFromDB() {
        ArrayList<Integer> list;
        ArrayList<String> param = new ArrayList<>();
        StoreSQL sql = new StoreSQL("jdbc:sqlite:xml.s3db");
        sql.connect();
        param.add("987654321");
        int numRowsUpdated = sql.updateDB("insert into entry (field) values (?)", param);
        assertThat(numRowsUpdated, is(1));
        list = sql.getColumn("SELECT field FROM entry WHERE field = '987654321'", new ArrayList<>(), "field");
        assertThat(list.get(0), is(987654321));
        sql.updateDB("DELETE FROM entry WHERE field = '987654321'", new ArrayList<>());
        sql.close();
    }
}
