package ru.job4j.list;

import org.junit.Test;
import java.util.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class UserConvertTest {

    /**
     * Тест проверяет корректность преобразования списка объектов User в HashMap
     */
    @Test
    public void whenListOfUserThenHashMap() {
        UserConvert userConvert = new UserConvert();
        List<UserC> list = new ArrayList<>();
        UserC user1 = new UserC(222, "ivan", "msk");
        UserC user2 = new UserC(111, "sasha", "ny");
        UserC user3 = new UserC(333, "kolya", "nsk");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        HashMap<Integer, UserC> expect = new HashMap<>();
        expect.put(111, user2);
        expect.put(222, user1);
        expect.put(333, user3);
        HashMap<Integer, UserC> result = userConvert.process(list);
        assertThat(result, is(expect));
    }
    /**
     * Тест проверяет корректность преобразования списка объектов User в HashMap
     */
    @Test
    public void getterAndSetterTest() {
        UserC userC = new UserC(222, "ivan", "msk");
        userC.setCity("nsk");
        userC.setName("max");
        userC.setId(2);
        assertThat(userC.getName(), is("max"));
        assertThat(userC.getCity(), is("nsk"));
        assertThat(userC.getId(), is(2));
    }
}
