package ru.job4j.diff;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 14.10.2018
 */
public class StoreTest {
    Store.User user1 = new Store.User(1, "test1");
    Store.User user2 = new Store.User(2, "test2");
    Store.User user3 = new Store.User(3, "test3");
    Store.User user4 = new Store.User(4, "test4");
    Store.User user5 = new Store.User(2, "test4");
    List<Store.User> previous = new ArrayList<>();
    List<Store.User> current = new ArrayList<>();
    Store store = new Store();

    @Before
    public void setUp() {
        previous.add(user1);
        previous.add(user2);
        previous.add(user3);
        current.add(user1);
        current.add(user2);
        current.add(user3);
    }

    @Test
    public void whenCurrentBiggerPreviousThanGetNewUsers() {
        current.add(user4);

        Store.Info info = store.diff(previous, current);
        assertThat(info.getNewUsers(), is(1));
        assertThat(info.getRemovedUsers(), is(0));
        assertThat(info.getModifiedUsers(), is(0));
    }

    @Test
    public void whenCurrentSmallerPreviousThanGetRemovedUsers() {
        previous.add(user4);
        Store.Info info = store.diff(previous, current);
        assertThat(info.getNewUsers(), is(0));
        assertThat(info.getRemovedUsers(), is(1));
        assertThat(info.getModifiedUsers(), is(0));
    }

    @Test
    public void whenCurrentModifiedPreviousThanGetModifiedUsers() {
        current.remove(user2);
        current.add(user5);
        Store.Info info = store.diff(previous, current);
        assertThat(info.getNewUsers(), is(0));
        assertThat(info.getRemovedUsers(), is(0));
        assertThat(info.getModifiedUsers(), is(1));
    }

    @Test
    public void whenCurrentDifferPreviousThanGetDifferents() {
        previous.remove(user1);
        current.remove(user2);
        current.remove(user3);
        current.add(user5);
        Store.Info info = store.diff(previous, current);
        assertThat(info.getNewUsers(), is(1));
        assertThat(info.getRemovedUsers(), is(1));
        assertThat(info.getModifiedUsers(), is(1));
    }
}
