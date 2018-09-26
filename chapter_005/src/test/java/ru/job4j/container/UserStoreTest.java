package ru.job4j.container;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;


/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 26.09.2018
 */
public class UserStoreTest {
    UserStore userStore;
    User test;
    User test1;

    @Before
    public void setUp() {
        userStore = new UserStore(2);
        test = new User("test");
        test1 = new User("test1");
        userStore.add(test);
        userStore.add(test1);
    }

    /**
     *  Тест добавления элементов в коллекцию
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddElementTest() {
        assertThat(userStore.findById("test"), is(test));
        userStore.add(new User("test2"));
    }

    /**
     *  Тест замены элемента в коллекции
     */
    @Test
    public void whenSetElementTest() {
        User test2 = new User("test2");
        assertThat(userStore.replace("test", test2), is(true));
        assertThat(userStore.findById("test2"), is(test2));
    }

    /**
     *  Тест удаления элемента из коллекции
     */
    @Test
    public void whenDeleteElementTest() {
        assertThat(userStore.delete("test"), is(true));
        assertThat(userStore.findById("test"), is(nullValue()));
    }

    /**
     *  Тест поиска элемента по id
     */
    @Test
    public void whenGetThenTest() {
        assertThat(userStore.findById("test"), is(test));
        assertThat(userStore.findById("test2"), is(nullValue()));
    }
}
