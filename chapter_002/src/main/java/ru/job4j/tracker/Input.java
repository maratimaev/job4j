package ru.job4j.tracker;

import java.util.List;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public interface Input {
    /**
     * Метод запрашивает пункт меню.
     * @param question приглашение типа String
     * @return Введенная строка
     */
    String ask(String question);
    /**
     * Метод возвращает пункт меню.
     * @param question приглашение типа String
     * @param range список возможных пунктов меню типа Integer
     * @return пункт меню типа int
     */
    int ask(String question, List<Integer> range);
}
