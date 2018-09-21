package ru.job4j.iterator;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.MatrixIterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 20.09.2018
 */
public class MatrixIteratorTest {

    private Iterator<Integer> it;

    @Before
    public void setUp() {
        it = new MatrixIterator(new int[][]{{1, 2, 3}, {4, 5, 6}});
    }

    /**
     * Тест проверяет возвращение всех элементов в массиве
     */
    @Test
    public void hasNextNextSequentialInvocation() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(false));
    }

    /**
     * Тест проверяет перебор массива без обращения к методу hasNext()
     */
    @Test
    public void testsThatNextMethodDoesntDependsOnPriorHasNextInvocation() {
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(6));
    }

    /**
     * Тест проверяет влияние hasNext() на перебор массива
     */
    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(6));
    }

    /**
     * Тест проверяет корректность создания Exception при невозможности обратиться к следующему элементу
     */
    @Test(expected = NoSuchElementException.class)
    public void shoulThrowNoSuchElementException() {
        it = new MatrixIterator(new int[][]{});
        it.next();
    }

}
