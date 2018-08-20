package ru.job4j.tracker;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public abstract class BaseAction implements UserAction {
    private int menuKey;
    private String menuString;

    /**
     * Конструктор.
     * @param menuKey объект типа int
     * @param menuString объект типа String
     */
    public BaseAction(int menuKey, String menuString) {
        this.menuKey = menuKey;
        this.menuString = menuString;
    }

    /**
     * Метод возвращает информацию о данном пункте меню.
     * @return Строка меню
     */
    @Override
    public String info() {
        return this.menuKey + this.menuString;
    }
}
