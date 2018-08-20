package ru.job4j.tracker;

import java.util.*;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */

public class Tracker {
    private final Item[] items = new Item[100];
    private int position = 0;
	private static final Random RN = new Random();

    /**
     * Метод добавляет заявку
     * @param item типа Item
     * @return заявку типа Item
     */
	public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Метод возвращает найденную заявку по id.
     * @param id типа String
     * @return заявку типа Item
     */
    protected Item findById(String id) {
    	Item result = null;
    	for (Item item : items) {
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
	public Item[] getAll() {
		return Arrays.copyOf(this.items, this.position);
	}

    /**
     * Метод редактирует заявку
     * @param id типа String
     * @param item типа Item
     */
	public void replace(String id, Item item) {
	    for (int i = 0; i != this.position; i++) {
			if (this.items[i].getId().equals(id)) {
                item.setId(id);
				this.items[i] = item;
				break;
			}
		}
	}

    /**
     * Метод удаляет заявку
     * @param id типа String
     */
	public void delete(String id) {
        for (int i = 0; i != this.position; i++) {
			if (this.items[i].getId().equals(id)) {
				System.arraycopy(this.items, i + 1, this.items, i, this.items.length - 1 - i);
				this.position--;
				break;
			}
		}
	}

    /**
     * Метод ищет заявки по имени
     * @param key типа String
     * @return заявки типа Item[]
     */
	public Item[] findByName(String key) {
		Item[] array = new Item[this.position];
		int i = 0;
		for (Item item : items) {
			if (item != null && item.getName().equals(key)) {				
				array[i++] = item;
			}
		}
		return Arrays.copyOf(array, i);
	}
}