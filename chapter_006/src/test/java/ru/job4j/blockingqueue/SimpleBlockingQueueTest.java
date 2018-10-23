package ru.job4j.blockingqueue;

import org.junit.Test;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 23.10.2018
 */
public class SimpleBlockingQueueTest {
    @Test
    public void whenCreate2ThreadsThenQueueWorks() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread threadP = new Thread(new Producer(queue));
        Thread threadC = new Thread(new Consumer(queue));
        threadP.start();
        threadC.start();
        threadP.join();
        threadC.join();
    }
}
