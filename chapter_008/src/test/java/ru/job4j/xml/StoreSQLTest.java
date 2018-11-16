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
        Config config = new Config();
        config.init();
        try (StoreSQL sql = new StoreSQL(config)) {
            assertThat(sql.connect(), is(true));
        }
    }

    @Test
    public void checkTableExists() {
        Config config = new Config();
        config.init();
        try (StoreSQL sql = new StoreSQL(config)) {
            sql.connect();
            assertThat(sql.tableNotExists("entry"), is(false));
        }
    }

    @Test
    public void checkGenerate() {
        ArrayList<Integer> list;
        Config config = new Config();
        config.init();
        try (StoreSQL sql = new StoreSQL(config)) {
            sql.generate(2);
            sql.connect();
            list = sql.getColumn("SELECT field FROM entry", new ArrayList<>(), "field");
            assertThat(list.size(), is(3));
        }
    }

    @Test
    public void whenUpdateDBThenGetThemFromDB() {
        ArrayList<Integer> list;
        ArrayList<String> param = new ArrayList<>();
        Config config = new Config();
        config.init();
        try (StoreSQL sql = new StoreSQL(config)) {
            sql.connect();
            param.add("987654321");
            int numRowsUpdated = sql.updateDB("insert into entry (field) values (?)", param);
            assertThat(numRowsUpdated, is(1));
            list = sql.getColumn("SELECT field FROM entry WHERE field = '987654321'", new ArrayList<>(), "field");
            assertThat(list.get(0), is(987654321));
            sql.updateDB("DELETE FROM entry WHERE field = '987654321'", new ArrayList<>());
        }
    }
}
