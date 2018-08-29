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
    /** Геттер возвращает имя пользователя
     * @return типа String
     */
    public String getName() {
        return name;
    }
    /** Геттер возвращает город пользователя
     * @return типа String
     */
    public String getCity() {
        return city;
    }
    /** Сеттер прописывает id пользователя
    *  @param id типа int
    */
    public void setId(int id) {
        this.id = id;
    }
    /** Сеттер прописывает имя пользователя
     * @param name типа String
     */
    public void setName(String name) {
        this.name = name;
    }
    /** Сеттер прописывает город пользователя
     * @param city типа String
     */
    public void setCity(String city) {
        this.city = city;
    }
}
