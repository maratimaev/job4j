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

    Comparator<User> compareByNameLength = new Comparator<User>() {
        @Override
        public int compare(User o1, User o2) {
            return Integer.compare(o1.getName().length(), o2.getName().length());
        }
    };

    public List<User> sortNameLength(List<User> list) {
        Collections.sort(list, compareByNameLength);
        return list;
    }

    Comparator<User> compareByNameLengthThenAge = new Comparator<User>() {
        @Override
        public int compare(User o1, User o2) {
            int result = Integer.compare(o1.getName().length(), o2.getName().length());
            result = result != 0 ? result : Integer.compare(o1.getAge(), o2.getAge());
            return result;
        }
    };

    public List<User> sortByAllFields(List<User> list) {
        Collections.sort(list, compareByNameLengthThenAge);
        return list;
    }
}
