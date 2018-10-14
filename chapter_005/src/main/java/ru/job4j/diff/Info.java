package ru.job4j.diff;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 14.10.2018
 */
public class Info {
    /**
     * Количество новых пользователей
     */
    private int newUsers;
    /**
     * Количество удаленных пользователей
     */
    private int removedUsers;
    /**
     * Количество пользователей с одинаковыми id, но разными name
     */
    private int modifiedUsers;
    /**
     * Преобразованный в карту предыдущий список
     */
    Map<Integer, String> previous = new HashMap<>();
    /**
     * Преобразованный в карту текущий список
     */
    Map<Integer, String> current = new HashMap<>();

    public Info(List<Store.User> previous, List<Store.User> current) {
        for (Store.User user: previous) {
            this.previous.put(user.getId(), user.getName());
        }
        for (Store.User user: current) {
            this.current.put(user.getId(), user.getName());
        }
    }

    public int getNewUsers() {
        return newUsers;
    }

    public int getRemovedUsers() {
        return removedUsers;
    }

    public int getModifiedUsers() {
        return modifiedUsers;
    }

    /**
     * Вычисление статистики
     */
    public void calc() {
        for (int curr : this.current.keySet()) {
            if (!previous.containsKey(curr)) {
                newUsers++;
            } else if (!current.get(curr).equals(previous.get(curr))) {
                modifiedUsers++;
            }
        }
        for (int prev : this.previous.keySet()) {
            if (!current.containsKey(prev)) {
                removedUsers++;
            }
        }
    }
}
