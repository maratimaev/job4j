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

        UserS user1 = new UserS("ivan", 20);
        UserS user2 = new UserS("sasha", 30);
        UserS user3 = new UserS("max", 25);
        List<UserS> list = Arrays.asList(user1, user2, user3);

        Set<UserS> expect = new TreeSet<>();
        expect.add(user1);
        expect.add(user2);
        expect.add(user3);
        Set<UserS> result = sortUser.sort(list);
        assertEquals(result, expect);
    }

    /**
     * Тест проверяет сравнение 2х пользователей по возрасту
     */
    @Test
    public void compareUsersAge() {
        assertThat(new UserS("ivan", 20).compareTo(new UserS("ivan", 21)),
                is(-1));
    }

    /**
     * Тест проверяет сравнение пользователей по длине имени
     */
    @Test
    public void sortUsersNameByLength() {
        SortUser sortUser = new SortUser();

        UserS user1 = new UserS("ivan", 20);
        UserS user2 = new UserS("sasha", 30);
        UserS user3 = new UserS("max", 25);
        List<UserS> list = Arrays.asList(user1, user2, user3);

        List<UserS> expect = new ArrayList<>();
        expect.add(user3);
        expect.add(user1);
        expect.add(user2);

        List<UserS> result = sortUser.sortNameLength(list);
        assertThat(result, is(expect));
    }

    /**
     * Тест проверяет сравнение пользователей по длине имени и возрасту
     */
    @Test
    public void sortUsersNameByLengthThenAge() {
        SortUser sortUser = new SortUser();

        UserS user1 = new UserS("ivan", 30);
        UserS user2 = new UserS("sasha", 30);
        UserS user3 = new UserS("max", 25);
        UserS user4 = new UserS("ivan", 25);
        List<UserS> list = Arrays.asList(user1, user2, user3, user4);

        List<UserS> expect = new ArrayList<>();
        expect.add(user4);
        expect.add(user1);
        expect.add(user3);
        expect.add(user2);

        List<UserS> result = sortUser.sortByAllFields(list);
        assertThat(result, is(expect));
    }
}
