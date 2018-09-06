package ru.job4j.sortuser;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class UserS implements Comparable {
    /** Поле имя пользователя */
    private String name;
    /** Поле возраст пользователя */
    private int age;

    public UserS(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    /**
     * Метод для сравнения 2х пользователей по возрасту
     * @param o типа User
     * @return int -1, 0, 1
     */
    @Override
    public int compareTo(Object o) {
        UserS compare = (UserS) o;
        return Integer.compare(this.age, compare.getAge());
    }
}
