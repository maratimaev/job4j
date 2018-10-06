package ru.job4j.simplequeue;

import org.junit.Test;
import ru.job4j.simple.queue.SimpleQueue;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class SimpleQueueTest {

    SimpleQueue<String> simpleQueue = new SimpleQueue<>();

    @Test
    public void whenPushElementsThenPollThem() {
        simpleQueue.push("test");
        simpleQueue.push("test1");
        simpleQueue.push("test2");
        simpleQueue.push("test3");

        assertThat(simpleQueue.poll(), is("test"));
        assertThat(simpleQueue.poll(), is("test1"));
        assertThat(simpleQueue.poll(), is("test2"));
        assertThat(simpleQueue.poll(), is("test3"));
        assertThat(simpleQueue.poll(), is(nullValue()));
    }
}
