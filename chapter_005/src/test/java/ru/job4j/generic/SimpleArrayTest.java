package ru.job4j.generic;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 24.09.2018
 */
public class SimpleArrayTest {
    public SimpleArray<String> saV;

    @Before
    public void setUp() {
        saV = new SimpleArray<>(2);
    }

    /**
     *  Тест добавления элементов в коллекцию
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddElementTest() {
        saV.add("www");
        saV.add("aaa");
        assertThat(saV.get(1), is("aaa"));
        saV.add("sss");
    }

    /**
     *  Тест замены элемента в коллекции
     */
    @Test
    public void whenSetElementTest() {
        saV.add("www");
        saV.add("aaa");
        assertThat(saV.set(0, "sss"), is(true));
        assertThat(saV.get(0), is("sss"));
    }

    /**
     *  Тест удаления элемента из коллекции
     */
    @Test
    public void whenDeleteElementTest() {
        saV.add("www");
        saV.add("aaa");
        assertThat(saV.delete(0), is(true));
        assertThat(saV.get(0), is("aaa"));
    }

    /**
     *  Тест получения элемента коллекции
     */
    @Test(expected = NoSuchElementException.class)
    public void whenGetThenTest() {
        saV.add("www");
        saV.add("aaa");
        assertThat(saV.get(0), is("www"));
        assertThat(saV.get(1), is("aaa"));
        saV.get(2);
    }

    /**
     *  Тест работы итератора
     */
    @Test(expected = NoSuchElementException.class)
    public void whenIterateThenTest() {
        saV.add("www");
        saV.add("aaa");
        assertThat(saV.get(0), is("www"));
        assertThat(saV.get(1), is("aaa"));

        Iterator it = saV.iterator();
        assertThat(it.hasNext(), Matchers.is(true));
        assertThat(it.next(), Matchers.is("www"));
        assertThat(it.hasNext(), Matchers.is(true));
        assertThat(it.next(), Matchers.is("aaa"));
        assertThat(it.hasNext(), Matchers.is(false));
        it.next();
    }

    /**
     * Тест получения индекса элемента
     */
    @Test
    public void indexOf() {
        saV.add("www");
        saV.add("aaa");
        assertThat(saV.indexOf("aaa"), is(1));
    }
}

