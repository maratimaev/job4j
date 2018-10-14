package ru.job4j.diff;

import java.util.List;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 14.10.2018
 */
public class Store {
    static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    /** Сравнение 2х списков
     * @param previous предыдущий список
     * @param current текущий список
     * @return статистика Info
     */
    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info(previous, current);
        info.calc();
        return info;
    }

}