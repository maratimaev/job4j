package ru.job4j.list.dynlinked;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.list.dynlinked.DynLinkedList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 28.09.2018
 */
public class DynLinkedListTest {
    private DynLinkedList<Integer> list;

    @Before
    public void beforeTest() {
        list = new DynLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    /**
     * Проверка получения элемента по положению в списке
     */
    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(3));
        assertThat(list.get(3), is(nullValue()));
    }

    /**
     * Проверка получения размера списка
     */
    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    /**
     *  Проверка удаления элемента
     */
    @Test
    public void whenDeleteElementThenReturnHisData() {
        list.add(4);
        list.add(5);
        assertThat(list.delete(0), is(1));
        assertThat(list.delete(3), is(5));
        assertThat(list.delete(1), is(3));
        assertThat(list.delete(0), is(2));
        assertThat(list.delete(0), is(4));
        assertThat(list.delete(5), is(nullValue()));
    }

    /**
     * Проверка работы итератора
     */
    @Test
    public void whenItarateThenGetElements() {
        Iterator iterator = list.iterator();

        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
    }

    /**
     * Проверка выхода за границы списка в итераторе
     */
    @Test (expected = NoSuchElementException.class)
    public void whenItarateThenGetNoSuchElementException() {
        Iterator iterator = list.iterator();

        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        iterator.next();
    }

    /**
     * Проверка контроля изменения списка во время работы итератора
     */
    @Test (expected = ConcurrentModificationException.class)
    public void whenItarateThenGetConcurrentModificationException() {
        Iterator iterator = list.iterator();

        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        list.add(4);
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
    }
}
