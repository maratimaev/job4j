package ru.job4j.sql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

/** Реализация работы с заявками из 2го модуля в базе PostgreSQL
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 31.10.2018
 */
public class TrackerSQL implements ITracker, Cloneable {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrackerSQL.class.getName());

    /**
     * Экземпляр соединения с БД
     */
    private Connection connection;

    /** Установка соединения с БД
     * @return результат boolean
     */
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
        checkTableExistance();
        return this.connection != null;
    }

    /**
     *  Проверка на существовfние таблицы items
     */
    private void checkTableExistance() {
        Statement st = null;
        try {
            DatabaseMetaData dbmd = this.connection.getMetaData();
            ResultSet rsTables = dbmd.getColumns(null, null, "items", "%");
            if (!rsTables.next()) {
                st = this.connection.createStatement();
                st.execute("CREATE TABLE items (id serial PRIMARY KEY, name VARCHAR(200), description VARCHAR(2000), item_id VARCHAR(200))");
                LOGGER.warn("Table 'items' not found. Empty table created");
            } else {
                LOGGER.info("Table 'items' founded");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            close(st);
        }
    }

    /** Добавление заявки в БД
     * @param item заявка
     * @return заявка
     */
    @Override
    public Item add(Item item) {
        PreparedStatement ps = null;
        item.setId(this.generateId());
        try {
            ps = this.connection.prepareStatement("INSERT INTO items (name, description, item_id) VALUES (?, ?, ?)");
            ps.setString(1, item.getName());
            ps.setString(2, item.getDescription());
            ps.setString(3, item.getId());
            int rs = ps.executeUpdate();
            LOGGER.info(String.format("Insert %s row: %s, %s, %s", rs, item.getName(), item.getDescription(), item.getId()));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            close(ps);
        }
        return item;
    }

    /** Изменения заявки по id
     * @param id заявки
     * @param item заявка
     */
    @Override
    public void replace(String id, Item item) {
        PreparedStatement ps = null;
        try {
            ps = this.connection.prepareStatement("UPDATE items SET name = ?, description = ? WHERE item_id = ?");
            ps.setString(1, item.getName());
            ps.setString(2, item.getDescription());
            ps.setString(3, id);
            int rs = ps.executeUpdate();
            LOGGER.info(String.format("Replaced %s row, %s", rs, id));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            close(ps);
        }
    }

    /** Удаление заявки
     * @param id заявки
     */
    @Override
    public void delete(String id) {
        PreparedStatement ps = null;
        try {
            ps = this.connection.prepareStatement("DELETE FROM items WHERE item_id = ?");
            ps.setString(1, id);
            int rs = ps.executeUpdate();
            LOGGER.info(String.format("Deleted %s row, %s", rs, id));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            close(ps);
        }
    }

    /** Выполнение запроса на чтение из БД
     * @param sql строка запроса
     * @param parameters параметры для prepareStatement
     * @return список заявок
     */
    private ArrayList<Item> query(String sql, List<String> parameters) {
        ArrayList<Item> result = new ArrayList<>();
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
                Item item = new Item(rs.getString("name"), rs.getString("description"));
                item.setId(rs.getString("item_id"));
                result.add(item);
                LOGGER.info(String.format("Get item %s, %s, %s", item.getName(), item.getDescription(), item.getId()));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            close(rs);
            close(ps);
        }
        return result;
    }

    /** Получить все заявки
     * @return список заявок
     */
    @Override
    public ArrayList<Item> getAll() {
        return query("SELECT name, description, item_id FROM items", new ArrayList<>());
    }

    /** Поиск заявки по имени
     * @param key имя заявки
     * @return список заявок
     */
    @Override
    public ArrayList<Item> findByName(String key) {
        List<String> parameters = new ArrayList<>();
        parameters.add(key);
        return query("SELECT name, description, item_id FROM items WHERE name = ?", parameters);
    }

    /** Поиск заявки по id
     * @param id заявки
     * @return заявка
     */
    @Override
    public Item findById(String id) {
        Item item = new Item();
        List<String> parameters = new ArrayList<>();
        parameters.add(id);
        ArrayList<Item> items = query("SELECT name, description, item_id FROM items WHERE item_id = ?", parameters);
        if (!items.isEmpty()) {
            item = items.get(0);
        }
        return item;
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
            if (this.connection != null) {
                this.connection.close();
                LOGGER.info("DB disconnected");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Метод генерирует id заявки
     * @return id новой заявки типа String
     */
    private String generateId() {
        Random rn = new Random();
        return String.valueOf(System.currentTimeMillis() + rn.nextInt());
    }
}
