package ru.job4j.generic;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest<K> {

    public SimpleArray<K> saT;
    @Before
    public void setUp() {
        saT = new SimpleArray<K>(2);
    }

    @Test
    public void IntegerTest() {
        Integer a = 5;
        Integer b = 7;
        whenAddElementTest(a, b);
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddElementTest(K a, K b) {
        saT.add(a);
        saT.add(b);
        assertThat(saT.get(1), is(b));
        saT.add(a);
    }

    @Test
    public void whenSetElementTest(T a, T b, T c) {
        saT.add(a);
        saT.add(b);
        saT.set(0, c);
        assertThat(saT.get(0), is(c));
    }

    @Test
    public void whenDeleteElementTest(T a, T b) {
        saT.add(a);
        saT.add(b);
        saT.delete(0);
        assertThat(saT.get(0), is(b));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenGetThenTest(T a, T b) {
        saT.add(a);
        saT.add(b);
        assertThat(saT.get(0), is(a));
        assertThat(saT.get(1), is(b));
        saT.get(2);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIterateThenTest(T a, T b) {
        saT.add(a);
        saT.add(b);
        assertThat(saT.get(0), is(a));
        assertThat(saT.get(1), is(b));

        Iterator<T> it = saT.iterator();
        assertThat(it.hasNext(), Matchers.is(true));
        assertThat(it.next(), Matchers.is(a));
        assertThat(it.hasNext(), Matchers.is(true));
        assertThat(it.next(), Matchers.is(b));
        assertThat(it.hasNext(), Matchers.is(false));
        it.next();
    }
}