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
public class RoleStoreTest {
    RoleStore roleStore;
    Role test;
    Role test1;

    @Before
    public void setUp() {
        roleStore = new RoleStore(2);
        test = new Role("test");
        test1 = new Role("test1");
        roleStore.add(test);
        roleStore.add(test1);
    }

    /**
     *  Тест добавления элементов в коллекцию
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddElementTest() {
        assertThat(roleStore.findById("test"), is(test));
        roleStore.add(new Role("test2"));
    }

    /**
     *  Тест замены элемента в коллекции
     */
    @Test
    public void whenSetElementTest() {
        Role test2 = new Role("test2");
        assertThat(roleStore.replace("test", test2), is(true));
        assertThat(roleStore.findById("test2"), is(test2));
    }

    /**
     *  Тест удаления элемента из коллекции
     */
    @Test
    public void whenDeleteElementTest() {
        assertThat(roleStore.delete("test"), is(true));
        assertThat(roleStore.findById("test"), is(nullValue()));
    }

    /**
     *  Тест поиска элемента по id
     */
    @Test
    public void whenGetThenTest() {
        assertThat(roleStore.findById("test"), is(test));
        assertThat(roleStore.findById("test2"), is(nullValue()));
    }
}
