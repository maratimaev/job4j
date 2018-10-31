package ru.job4j.sql;

import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Properties;
import java.util.function.BiPredicate;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 31.10.2018
 */
public class TrackerSQL implements ITracker, Cloneable {

    private Connection connection;

    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("username"),
                    config.getProperty("password"),
                    config.getProperty("url")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    @Override
    public Item add(Item item) {
        return null;
    }

    @Override
    public void replace(String id, Item item, BiPredicate<String, String> isEquals) {

    }

    @Override
    public void delete(String id, BiPredicate<String, String> isEquals) {

    }

    @Override
    public ArrayList<Item> getAll() {
        return null;
    }

    @Override
    public ArrayList<Item> findByName(String key, BiPredicate<String, String> isEquals) {
        return null;
    }

    @Override
    public Item findById(String id, BiPredicate<String, String> isEquals) {
        return null;
    }
}
