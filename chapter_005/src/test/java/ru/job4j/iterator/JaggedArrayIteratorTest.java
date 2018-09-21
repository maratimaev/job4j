package ru.job4j.iterator;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.MatrixIterator;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 20.09.2018
 */
public class JaggedArrayIteratorTest {
    private Iterator<Integer> it;

    @Before
    public void setUp() {
        it = new MatrixIterator(new int[][]{{1}, {3, 4}, {7}});
    }
    /**
     * Тест проверяет перебор массива без обращения к методу hasNext()
     */
    @Test
    public void testsThatNextMethodDoesntDependsOnPriorHasNextInvocation() {
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(7));
    }
    /**
     * Тест проверяет влияние hasNext() на перебор массива
     */
    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(7));
    }
    /**
     * Тест проверяет возвращение всех элементов в массиве
     */
    @Test
    public void hasNextNextSequentialInvocation() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(false));
    }
}
