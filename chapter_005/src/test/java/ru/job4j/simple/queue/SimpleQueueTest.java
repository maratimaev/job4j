package ru.job4j.simple.queue;

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

    @Test
    public void pushElementsOneByOther() {
        simpleQueue.push("1");
        assertThat(simpleQueue.poll(), is("1"));
        simpleQueue.push("2");
        assertThat(simpleQueue.poll(), is("2"));
        simpleQueue.push("3");
        assertThat(simpleQueue.poll(), is("3"));
    }

    @Test
    public void pushAndPollTwice() {
        simpleQueue.push("1");
        simpleQueue.push("2");
        simpleQueue.push("3");
        String poll = simpleQueue.poll();
        assertThat(poll, is("1"));
        simpleQueue.push("4");
        simpleQueue.push("5");
        poll = simpleQueue.poll();
        assertThat(poll, is("2"));
        poll = simpleQueue.poll();
        assertThat(poll, is("3"));
        poll = simpleQueue.poll();
        assertThat(poll, is("4"));
        poll = simpleQueue.poll();
        assertThat(poll, is("5"));
    }
}
