package ru.job4j.parallelsearch;

import ru.job4j.blockingqueue.SimpleBlockingQueue;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 24.10.2018
 */
public class ParallelSearch {
    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        final Thread consumer = new Thread(
                () -> {
                    try {
                        while (true) {
                            System.out.println(queue.poll());
                        }
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                    }
                }
        );
        consumer.start();
        new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        queue.offer(index);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    consumer.interrupt();
                }
        ).start();
    }
}
