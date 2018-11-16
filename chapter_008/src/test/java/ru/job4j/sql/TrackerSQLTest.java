package ru.job4j.sql;

import org.junit.Test;
import ru.job4j.tracker.Item;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 01.11.2018
 */
public class TrackerSQLTest {

    @Test
    public void checkConnection() {
        try (TrackerSQL sql = new TrackerSQL()) {
            assertThat(sql.init(), is(true));
        }
    }

    @Test
    public void whenAddItemToDB() {
        try (TrackerSQL sql = new TrackerSQL()) {
            sql.init();
            Item item = new Item("test2", "desc2");
            assertThat(sql.add(item), is(item));
            sql.delete(item.getId());
        }
    }

    @Test
    public void whenEditItemWriteToDB() {
        try (TrackerSQL sql = new TrackerSQL()) {
            sql.init();
            Item itemForReplace = sql.add(new Item("replace", "replace"));
            Item item = new Item("test3", "desc3");
            sql.replace(itemForReplace.getId(), item);
            sql.delete(itemForReplace.getId());
        }
    }

    @Test
    public void whenDeleteItemDeleteFromDB() {
        try (TrackerSQL sql = new TrackerSQL()) {
            sql.init();
            Item itemForDelete = sql.add(new Item("delete", "delete"));
            sql.delete(itemForDelete.getId());
        }
    }

    @Test
    public void whenGetAllItemsFromDB() {
        try (TrackerSQL sql = new TrackerSQL()) {
            sql.init();
            List<Item> items = sql.getAll();
            assertThat(items.isEmpty(), is(false));
        }
    }

    @Test
    public void whenFindByNameInDB() {
        try (TrackerSQL sql = new TrackerSQL()) {
            sql.init();
            Item itemForSearch = sql.add(new Item("name", "name"));
            ArrayList<Item> items = sql.findByName("name");
            assertThat(items.isEmpty(), is(false));
            sql.delete(itemForSearch.getId());
        }
    }

    @Test
    public void whenFindByIdInDB() {
        try (TrackerSQL sql = new TrackerSQL()) {
            sql.init();
            Item itemForSearch = sql.add(new Item("id", "id"));
            Item item = sql.findById(itemForSearch.getId());
            assertThat(item.getName(), is("id"));
            sql.delete(itemForSearch.getId());
        }
    }
}
