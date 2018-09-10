package ru.job4j.departments;

import java.util.TreeSet;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class Departments {
    /** Поле упорядоченный Set подразделений */
    private TreeSet<String> result;

    public Departments(TreeSet<String> result) {
        this.result = result;
    }

    /**
     * Метод сравнивает 2 строки посимвольно
     * @param strArray типа String[]
     * @return отсортированный массив, с добавлением отсутствующих подразделений String[]
     */
    public String[] sort(String[] strArray) {
        for (String str: strArray) {
            StringBuilder k = new StringBuilder();
            for (String s: splitDepartments(str)) {
                result.add(k + s);
                k.append(s).append("\\");
            }
        }
        return result.toArray(new String[0]);
    }

    /**
     * Метод разделяет строки на подразделения
     * @param str типа String
     * @return массив подразделений String[]
     */
    private String[] splitDepartments(String str) {
        return str.split("\\\\");
    }
}
