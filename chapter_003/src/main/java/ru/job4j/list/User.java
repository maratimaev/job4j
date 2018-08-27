package ru.job4j.list;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class User {
    /** Поле id пользователя */
    private int id;
    /** Поле имя пользователя */
    private String name;
    /** Поле город пользователя */
    private String city;

    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    /** Геттер возвращает id пользователя
     * @return типа int
     */
    public int getId() {
        return id;
    }

}
