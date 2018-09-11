package ru.job4j.departments;

import java.util.Comparator;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
class ReverseDepartments implements Comparator<String> {
    /**
     * Компаратор для обратной сортировки departments
     * @param o1 типа String
     * @param o2 типа String
     * @return int
     */
    public int compare(String o1, String o2) {
        return o2.compareTo(o1);
    }
}
