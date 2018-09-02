package ru.job4j.comparator;

import java.util.Comparator;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class ListCompare implements Comparator<String> {

    /**
     * Метод сравнивает 2 строки посимвольно
     * @param s1 типа String
     * @param s2 типа String
     * @return int
     */
    public int compare(String s1, String s2) {
        int result = 0;
        for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
            result = Character.compare(s1.charAt(i), s2.charAt(i));
            if (result != 0) {
                break;
            }
        }
        if (result == 0) {
            result = s1.length() - s2.length();
        }
        return result;
    }
}
