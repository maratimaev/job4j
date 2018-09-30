package ru.job4j.objarraylist;

import org.junit.Test;
import org.junit.Before;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 27.09.2018
 */
public class ObjArrayListTest {

    private ObjArrayList<String> strArrayList;

    @Before
    public void setUp() {
        strArrayList = new ObjArrayList<>();
    }

    /**
     * Проверка добавления и получения элемнтов
     */
    @Test
    public void whenAddElementToContainerThenGetHim() {
        strArrayList.add("test");
        strArrayList.add("test1");
        strArrayList.add("test2");
        assertThat(strArrayList.get(1), is("test1"));
        assertThat(strArrayList.get(3), is(nullValue()));
    }

    /**
     * Проверка работы итератора
     */
    @Test
    public void whenItarateThenGetElements() {
        strArrayList.add("test");
        strArrayList.add("test1");
        strArrayList.add("test2");

        Iterator iterator = strArrayList.iterator();

        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("test"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("test1"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("test2"));
    }

    /**
     * Проверка выхода за границы массива в итераторе
     */
    @Test (expected = NoSuchElementException.class)
    public void whenItarateThenGetNoSuchElementException() {
        strArrayList.add("test");

        Iterator iterator = strArrayList.iterator();

        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("test"));
        assertThat(iterator.hasNext(), is(false));
        iterator.next();
    }

    /**
     * Проверка контроля изменения массива во время работы итератора
     */
    @Test (expected = ConcurrentModificationException.class)
    public void whenItarateThenGetConcurrentModificationException() {
        strArrayList.add("test");
        strArrayList.add("test1");

        Iterator iterator = strArrayList.iterator();

        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("test"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("test1"));
        strArrayList.add("test2");
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("test2"));
    }
}
