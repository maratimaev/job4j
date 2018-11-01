package ru.job4j.sql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(TrackerSQL.class.getName());

    private Connection connection;

    public boolean init() {
        LOGGER.info("Connect to DB");
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER.info("Connected OK " + this.connection);
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
