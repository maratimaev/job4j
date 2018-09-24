package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    @Before
    public void setUp() {

    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddElementTest() {
        SimpleArray<Integer> saInteger;
        saInteger = new SimpleArray<>(2);
        saInteger.add(5);
        saInteger.add(7);
        assertThat(saInteger.get(1), is(7));
        saInteger.add(0);
    }

    @Test
    public void whenSetElementTest() {
        SimpleArray<Integer> saInteger;
        saInteger = new SimpleArray<>(2);
        saInteger.add(5);
        saInteger.add(7);
        saInteger.set(0, 10);
        assertThat(saInteger.get(0), is(10));
    }

    @Test
    public void whenDeleteElementTest() {
        SimpleArray<Integer> saInteger;
        saInteger = new SimpleArray<>(2);
        saInteger.add(5);
        saInteger.add(7);
        saInteger.delete(0);
        assertThat(saInteger.get(0), is(7));
        assertThat(saInteger.get(1), is((Class<Integer>) null));
    }

    @Test
    public void get() {
    }

    @Test
    public void iterator() {
    }
}