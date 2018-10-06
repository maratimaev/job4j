package ru.job4j.simplestack;

import org.junit.Test;
import ru.job4j.simple.stack.SimpleStack;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 28.09.2018
 */
public class SimpleStackTest {
    SimpleStack<String> stack = new SimpleStack<>();

    @Test
    public void whenPushElementsThenPollThem() {
        stack.push("test");
        stack.push("test1");
        stack.push("test2");

        assertThat(stack.poll(), is("test2"));
        assertThat(stack.poll(), is("test1"));
        assertThat(stack.poll(), is("test"));
        assertThat(stack.poll(), is(nullValue()));
    }
}
