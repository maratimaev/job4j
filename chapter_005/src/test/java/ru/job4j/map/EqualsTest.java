package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 07.10.2018
 */
public class EqualsTest {

    private Map<User, Object> map = new HashMap<>();

    @Test
    public void whenPutTwoObjectsThenPrintThem() {
        User user1 = new User("petya", 3,
                new GregorianCalendar(1982, Calendar.JANUARY, 21));
        User user2 = new User("petya", 3,
                new GregorianCalendar(1982, Calendar.JANUARY, 21));
        map.put(user1, 1);
        map.put(user2, 2);

        System.out.println(map);
        /* Значения полей объектов user1, user2 одинаковы, поэтому можно предположить,
            что добавление user2 перезапишет user1 и в итоге в карте останется по прежнему один элемент.
            Переопределение hashcode позволило получить одинаковые коды для объектов user1, user2.
            Тем не менее для сравнения объектов в HashMap используются не только hash, но и значения самих объектов
            if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;

            Поэтому в выводе видно, что хэши одинаковые, но добавляются в карту все равно оба объекта.
            Для полного сравнения необъодимо переопределить еще и equals
         */
    }
}
