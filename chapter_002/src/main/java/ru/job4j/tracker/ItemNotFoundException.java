package ru.job4j.tracker;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class ItemNotFoundException extends RuntimeException {
    /**
     * Метод обрабатывает неверный id.
     * @param msg типа String
     */
    public ItemNotFoundException(String msg) {
        super(msg);
    }
}
