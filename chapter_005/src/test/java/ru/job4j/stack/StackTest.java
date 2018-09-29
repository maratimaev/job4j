package ru.job4j.stack;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class StackTest {
    Stack<String> stack = new Stack<>();

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
