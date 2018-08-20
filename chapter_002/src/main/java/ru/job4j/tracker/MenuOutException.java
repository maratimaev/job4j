package ru.job4j.tracker;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class MenuOutException extends RuntimeException {
    /**
     * Метод обрабатывает неверный пункт меню.
     * @param msg типа String
     */
    public MenuOutException(String msg) {
        super(msg);
    }
}
