package ru.job4j.simple.hashmap;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 07.10.2018
 */
public class SimpleHashMapTest {

    SimpleHashMap<String, Integer> simpleHashMap = new SimpleHashMap<>();

    @Test
    public void whenInsertElementThenGetResult() {
        assertThat(simpleHashMap.insert("test", 2), is(true));
        assertThat(simpleHashMap.insert("test", 3), is(false));
        assertThat(simpleHashMap.insert("test1", 4), is(true));
        assertThat(simpleHashMap.insert("test2", 4), is(true));
        assertThat(simpleHashMap.insert("test3", 4), is(true));
        assertThat(simpleHashMap.insert("test4", 4), is(true));
    }

    @Test
    public void whenInsertElementsThenGetThem() {
        simpleHashMap.insert("test", 2);
        simpleHashMap.insert("test1", 3);
        simpleHashMap.insert("test", 4);
        assertThat(simpleHashMap.get("test"), is(2));
        assertThat(simpleHashMap.get("test1"), is(3));
        assertThat(simpleHashMap.get("test2"), is(nullValue()));
    }

    @Test
    public void whenDeleteElementThenGetResult() {
        simpleHashMap.insert("test", 2);
        simpleHashMap.insert("test1", 3);
        simpleHashMap.insert("test2", 4);
        assertThat(simpleHashMap.delete("test1"), is(true));
        assertThat(simpleHashMap.delete("test3"), is(false));
        assertThat(simpleHashMap.delete("test"), is(true));
    }
}
