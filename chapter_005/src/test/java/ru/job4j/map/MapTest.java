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
public class MapTest {

    private Map<User, Object> map = new HashMap<>();

    @Test
    public void whenPutTwoObjectsThenPrintThem() {
        User user1 = new User("max", 1,
                new GregorianCalendar(1990, Calendar.JANUARY, 11));
        User user2 = new User("max", 1,
                new GregorianCalendar(1990, Calendar.JANUARY, 11));
        map.put(user1, 1);
        map.put(user2, 2);

        System.out.println(map);
        /* Значения полей объектов user1, user2 одинаковы, поэтому можно предположить,
            что добавление user2 перезапишет user1 и в итоге в карте останется по прежнему один элемент.
            Но так как метод equals не переопределен, то для сравнения объектов используется дефолтный
            метод equals Object, который генерирует hashcode для объектов User с использованием случайных чисел.
            Соответственно hashcode разные и добавляются оба объекта.
         */
    }
}
