package ru.job4j.sortuser;

import org.junit.Test;

import java.util.*;

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

    /**
     * Тест проверяет сравнение пользователей по длине имени
     */
    @Test
    public void sortUsersNameByLength() {
        SortUser sortUser = new SortUser();

        User user1 = new User("ivan", 20);
        User user2 = new User("sasha", 30);
        User user3 = new User("max", 25);
        List<User> list = Arrays.asList(user1, user2, user3);

        List<User> expect = new ArrayList<>();
        expect.add(user3);
        expect.add(user1);
        expect.add(user2);

        List<User> result = sortUser.sortNameLength(list);
        assertThat(result, is(expect));
    }

    /**
     * Тест проверяет сравнение пользователей по длине имени и возрасту
     */
    @Test
    public void sortUsersNameByLengthThenAge() {
        SortUser sortUser = new SortUser();

        User user1 = new User("ivan", 30);
        User user2 = new User("sasha", 30);
        User user3 = new User("max", 25);
        User user4 = new User("ivan", 25);
        List<User> list = Arrays.asList(user1, user2, user3, user4);

        List<User> expect = new ArrayList<>();
        expect.add(user3);
        expect.add(user4);
        expect.add(user1);
        expect.add(user2);

        List<User> result = sortUser.sortByAllFields(list);
        assertThat(result, is(expect));
    }
}
