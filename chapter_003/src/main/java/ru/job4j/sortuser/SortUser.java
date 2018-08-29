package ru.job4j.sortuser;

import java.util.*;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class SortUser {
    /**
     * Метод конвертирует список объектов User в TreeSet
     * @param list типа List<User>
     * @return TreeSet<User>
     */
    public Set<User> sort(List<User> list) {
        return new TreeSet(list);
    }



    public List<User> sortNameLength(List<User> list) {
        Comparator<User> compareByNameLength = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().length() - o2.getName().length();
            }
        };
        list.sort(compareByNameLength);
        return list;
    }

    public List<User> sortByAllFields(List<User> list) {
        Comparator<User> compareByNames = new Comparator<User>() {
            @Override
            public int compare (User o1, User o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        Comparator<User> compareByAges = new Comparator<User>() {
            @Override
            public int compare (User o1, User o2) {
                return o1.getAge() - o2.getAge();
            }
        };
        list.sort(compareByNames.thenComparing(compareByAges));
        return list;
    }
}
