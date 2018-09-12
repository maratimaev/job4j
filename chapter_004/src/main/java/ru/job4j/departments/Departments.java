package ru.job4j.departments;

import sun.reflect.generics.tree.Tree;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class Departments {
    /**
     * Метод сравнивает 2 строки посимвольно
     *
     * @param strArray типа String[]
     * @return отсортированный массив, с добавлением отсутствующих подразделений String[]
     */
    public String[] sort(String[] strArray, TreeSet<String> result) {
        for (String str : strArray) {
            String[] splited = str.split("\\\\");
            StringBuilder k = new StringBuilder();
            for (String s : splited) {
                result.add(k + s);
                k.append(s).append("\\");
            }
        }
        return result.toArray(new String[0]);
    }
}
