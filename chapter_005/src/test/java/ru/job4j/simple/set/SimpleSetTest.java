package ru.job4j.simple.set;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 06.10.2018
 */
public class SimpleSetTest {

    private SimpleSet<String> simpleSet = new SimpleSet<>();

    @Test (expected = NoSuchElementException.class)
    public void whenAddThreeUniqueReturnThreeElements() {
        simpleSet.add("test");
        simpleSet.add("test1");
        simpleSet.add("test2");

        Iterator it = simpleSet.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("test"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("test1"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("test2"));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test
    public void whenAddThreeUniqueAndTwoNonUniqueReturnThreeElements() {
        simpleSet.add("test");
        simpleSet.add("test1");
        simpleSet.add("test1");
        simpleSet.add("test2");
        simpleSet.add("test");

        Iterator it = simpleSet.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("test"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("test1"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("test2"));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenAddThreeNonUniqueElementsReturnOneElement() {
        simpleSet.add("test");
        simpleSet.add("test");
        simpleSet.add("test");

        Iterator it = simpleSet.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("test"));
        assertThat(it.hasNext(), is(false));
    }
}
