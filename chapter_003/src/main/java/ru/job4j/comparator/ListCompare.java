package ru.job4j.comparator;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class ListCompare {

    /**
     * Метод сравнивает 2 строки посимвольно
     * @param s1 типа String
     * @param s2 типа String
     * @return int
     */
    public int compare(String s1, String s2) {
        int result = 0;
        for (int i = 0; i < Math.max(s1.length(), s2.length()); i++) {
            result = Character.compare(s1.charAt(i), s2.charAt(i));
            if (result != 0) {
                break;
            }
            if ((i == s2.length() - 1) || (i == s1.length() - 1)) {
                result = s1.length() - s2.length();
                break;
            }
        }
        return result;
    }
}
