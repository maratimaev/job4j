package ru.job4j.search;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class Person {
    /** Поле имя  */
    private String name;
    /** Поле фамилия */
    private String surname;
    /** Поле номер телефона */
    private String phone;
    /** Поле адрес проживания */
    private String address;

    public Person(String name, String surname, String phone, String address) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }
    /**
     * Геттер имени
     * @return типа String
     */
    public String getName() {
        return name;
    }

    /**
     * Геттер фамилии
     * @return типа String
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Геттер номера телефона
     * @return типа String
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Геттер адреса проживания
     * @return типа String
     */
    public String getAddress() {
        return address;
    }
}
