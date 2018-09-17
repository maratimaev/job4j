package ru.job4j.departments;

import java.util.Arrays;
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
    public String[] parse(String[] strArray) {
        Set<String> result = new TreeSet<>();
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

    public String[] sortInAscOrder(String[] departments) {
        return parse(departments);
    }

    public String[] sortInDescOrder(String[] departments) {
        String[] result = parse(departments);
        Arrays.sort(result, new ReversDepsOrderComparator());
        return result;
    }

    private class ReversDepsOrderComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            int result = o2.split("\\\\")[0].compareTo(o1.split("\\\\")[0]);
            if (result == 0) {
                return o1.compareTo(o2);
            } else {
                return result;
            }
        }
    }
}
