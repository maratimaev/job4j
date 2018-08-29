package ru.job4j.sortuser;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class SortUserTest {
    /**
     * Тест проверяет конвертацию списка User в TreeSet
     */
    @Test
    public void sortUserListToTree() {
        SortUser sortUser = new SortUser();

        User user1 = new User("ivan", 20);
        User user2 = new User("sasha", 30);
        User user3 = new User("max", 25);
        List<User> list = Arrays.asList(user1, user2, user3);

        Set<User> expect = new TreeSet<>();
        expect.add(user1);
        expect.add(user2);
        expect.add(user3);
        Set<User> result = sortUser.sort(list);
        assertEquals(result, expect);
    }

    /**
     * Тест проверяет сравнение 2х пользователей по возрасту
     */
    @Test
    public void compareUsersAge() {
        assertThat(new User("ivan", 20).compareTo(new User("ivan", 21)),
                is(-1));
    }
}
