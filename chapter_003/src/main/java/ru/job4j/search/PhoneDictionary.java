package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class PhoneDictionary {
    private List<Person> persons = new ArrayList<Person>();

    /**
     * Метод добавляет запись в телефонный справочник
     * @param person типа Person
     */
    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список подощедщих пользователей.
     */
    public List<Person> find(String key) {
        List<Person> result = new ArrayList<>();
            for (Person value: this.persons) {
                if (value.getName().contains(key)
                        || value.getSurname().contains(key)
                        || value.getAddress().contains(key)
                        || value.getPhone().contains(key)) {
                    result.add(value);
                }
            }

        return result;
    }
}
