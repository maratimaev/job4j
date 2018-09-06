package ru.job4j.sortuser;

import java.util.*;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class SortUser {
    /**
     * Метод конвертирует список объектов UserS в TreeSet
     * @param list типа List<UserS>
     * @return TreeSet<UserS>
     */
    public Set<UserS> sort(List<UserS> list) {
        return new<UserS> TreeSet(list);
    }

    public List<UserS> sortNameLength(List<UserS> list) {
        Comparator<UserS> compareByNameLength = Comparator.comparingInt(o -> o.getName().length());
        list.sort(compareByNameLength);
        return list;
    }

    public List<UserS> sortByAllFields(List<UserS> list) {
        Comparator<UserS> compareByName = Comparator.comparing(UserS::getName);
        Comparator<UserS> compareByAge = Comparator.comparingInt(UserS::getAge);
        list.sort(compareByName.thenComparing(compareByAge));
        return list;
    }
}
