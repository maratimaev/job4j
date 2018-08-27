package ru.job4j.tracker;

import java.util.*;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */

public class Tracker {
    /** Поле список заявок */
    private final ArrayList<Item> items = new ArrayList<>();
    /** Поле случайно число для генерации id */
    private static final Random RN = new Random();

    /**
     * Метод добавляет заявку
     * @param item типа Item
     * @return заявку типа Item
     */
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
    protected Item findById(String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
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
    public ArrayList<Item> getAll() {
        return this.items;
    }

    /**
     * Метод редактирует заявку
     * @param id типа String
     * @param item типа Item
     */
    public void replace(String id, Item item) {
        ListIterator<Item> current = this.items.listIterator();
        while (current.hasNext()) {
            if (current.next().getId().equals(id)) {
                item.setId(id);
                current.set(item);
                break;
            }
        }
    }

    /**
     * Метод удаляет заявку
     * @param id типа String
     */
    public void delete(String id) {
        ListIterator<Item> current = this.items.listIterator();
        while (current.hasNext()) {
            if (current.next().getId().equals(id)) {
                current.remove();
                break;
            }
        }
    }

    /**
     * Метод ищет заявки по имени
     * @param key типа String
     * @return заявки типа Item[]
     */
    public ArrayList<Item> findByName(String key) {
        ListIterator<Item> current = this.items.listIterator();
        ArrayList<Item> result = new ArrayList<>();
        Item it;
        while (current.hasNext()) {
            it = current.next();
            if (it.getName().equals(key)) {
                result.add(it);
            }
        }
        return result;
    }
}