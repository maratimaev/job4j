package ru.job4j.pool;

import org.junit.Test;
import ru.job4j.blockingqueue.SimpleBlockingQueue;

import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 28.10.2018
 */
public class ThreadPoolTest {
    @Test
    public void whenStartManyThreadsGetJob() {
        ThreadPool threadPool = new ThreadPool();
    }
}
