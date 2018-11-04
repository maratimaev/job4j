package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.function.BiPredicate;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 31.10.2018
 */
public interface ITracker {
    Item add(Item item);
    void replace(String id, Item item);
    void delete(String id);
    ArrayList<Item> getAll();
    ArrayList<Item> findByName(String key);
    Item findById(String id);
}
