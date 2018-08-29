package ru.job4j.sortuser;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
}
