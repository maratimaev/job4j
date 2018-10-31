package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */

public class Tracker implements ITracker {
    /** Поле список заявок */
    private ArrayList<Item> items = new ArrayList<>();
    /** Поле случайно число для генерации id */
    private static final Random RN = new Random();
    /** Поле экземпляр класса tracker для синглетона*/
    private static Tracker uniqueTracker;

    private Tracker() {
    }

    /**
     * Метод возвращяет синглетон tracker
     * @return экземпляр класса tracker
     */
    public static Tracker getInstance() {
        if (uniqueTracker == null) {
            uniqueTracker = new Tracker();
        }
        return uniqueTracker;
    }

    /**
     * Метод удаляет синглетон tracker
     */
    @Test
    public static void resetTracker() {
        uniqueTracker = null;
    }

    /**
     * Метод добавляет заявку
     * @param item типа Item
     * @return заявку типа Item
     */
    @Override
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Метод возвращает найденную заявку по id.
     * @param id типа String
     * @return заявку типа Item
     */
    @Override
    public  Item findById(String id, BiPredicate<String, String> isEquals) {
        return this.items.stream()
                .filter(Objects::nonNull)
                .filter(item -> isEquals.test(item.getId(), id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Метод генерирует id заявки
     * @return id новой заявки типа String
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    /**
     * Метод возвращает массив заявок
     * @return заявки типа Item[]
     */
    @Override
    public ArrayList<Item> getAll() {
        return this.items;
    }

    /**
     * Метод редактирует заявку
     * @param id типа String
     * @param item типа Item
     */
    @Override
    public void replace(String id, Item item, BiPredicate<String, String> isEquals) {
        this.items = (ArrayList<Item>) this.items.stream()
                .map(i -> {
                    if (isEquals.test(i.getId(), id)) {
                        item.setId(id);
                        return item;
                    } else {
                        return i;
                    }
                }).collect(Collectors.toList());
    }

    /**
     * Метод удаляет заявку
     * @param id типа String
     */
    @Override
    public void delete(String id, BiPredicate<String, String> isEquals) {
        this.items = (ArrayList<Item>) this.items.stream()
                .filter(item -> !isEquals.test(item.getId(), id))
                .collect(Collectors.toList());
    }

    /**
     * Метод ищет заявки по имени
     * @param key типа String
     * @return заявки типа Item[]
     */
    @Override
    public ArrayList<Item> findByName(String key, BiPredicate<String, String> isEquals) {
        return (ArrayList<Item>) this.items.stream()
                .filter(item -> isEquals.test(item.getName(), key))
                .collect(Collectors.toList());
    }
}