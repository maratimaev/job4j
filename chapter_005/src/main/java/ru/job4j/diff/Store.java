package ru.job4j.diff;

import org.omg.CORBA.INTERNAL;

import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public interface Info {
        int getNewUsers();
        int getRemovedUsers();
        int getModifiedUsers();
    }

    /**
     * Сравнение 2х списков
     *
     * @param previous предыдущий список
     * @param current  текущий список
     * @return статистика Info
     */
    public Info diff(List<User> previous, List<User> current) {
        return new Info() {
            BiFunction<List<User>, List<User>, Integer> listUsers =
                    (c, p) -> (int) p.stream().filter(u -> !c.contains(u)).count();

            BiFunction<Map<Integer, String>, Map<Integer, String>, Integer> diffUsers =
                    (c, p) -> (int) p.keySet().stream().filter(key -> !c.containsKey(key)).count();

            BiFunction<Map<Integer, String>, Map<Integer, String>, Integer> modUsers =
                    (c, p) -> (int) p.keySet().stream().
                            filter(key -> c.containsKey(key) && !p.get(key).equals(c.get(key))).count();

            Function<List<User>, Map<Integer, String>> toHMap =
                    l -> l.stream().collect(Collectors.toMap(User::getId, User::getName));

            @Override
            public int getNewUsers() {
                return diffUsers.apply(toHMap.apply(previous), toHMap.apply(current));
            }

            @Override
            public int getRemovedUsers() {
                return diffUsers.apply(toHMap.apply(current), toHMap.apply(previous));
            }

            @Override
            public int getModifiedUsers() {
                return modUsers.apply(toHMap.apply(previous), toHMap.apply(current));
            }
        };
    }
}