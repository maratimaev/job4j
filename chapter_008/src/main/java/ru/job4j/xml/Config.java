package ru.job4j.xml;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 16.11.2018
 */
public class Config {
    private final Properties values = new Properties();

    /**
     * Чтение полей из файла app.properties
     */
    public void init() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("app.properties")) {
            values.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /** Получение значения поля
     * @param key поле
     * @return значение
     */
    public String get(String key) {
        return this.values.getProperty(key);
    }
}
