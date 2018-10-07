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
public class HashCodeTest {
    private Map<User, Object> map = new HashMap<>();

    @Test
    public void whenPutTwoObjectsThenPrintThem() {
        User user1 = new User("sasha", 2,
                new GregorianCalendar(1980, Calendar.JANUARY, 21));
        User user2 = new User("sasha", 2,
                new GregorianCalendar(1980, Calendar.JANUARY, 21));
        map.put(user1, 1);
        map.put(user2, 2);

        System.out.println(map);
        /* Значения полей объектов user1, user2 одинаковы, поэтому можно предположить,
            что добавление user2 перезапишет user1 и в итоге в карте останется по прежнему один элемент.

            Переопределили и equals, и hashcode.
            В выводе теперь присутствует только один элемент, потому что хеши одинаковые
            (вычисляются на основе полей User) и кроме того сравнивается содержимое объектов user1, user2.
         */
    }
}
