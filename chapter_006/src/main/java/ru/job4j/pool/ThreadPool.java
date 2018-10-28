package ru.job4j.pool;

import ru.job4j.blockingqueue.SimpleBlockingQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 28.10.2018
 */
public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public ThreadPool() {
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            this.threads.add(new Thread(new Consumer(this.tasks)));
        }
        for (Thread thread: this.threads) {
            thread.start();
            System.out.println("Started: " + thread.getName());
        }
        Thread producer = new Thread(new Producer(this.tasks));
        producer.start();

    }

    public void work(Runnable job) {
        job.run();
    }

    public void shutdown() {

    }
}

class Consumer implements Runnable {
    private SimpleBlockingQueue queue;
    public Consumer(SimpleBlockingQueue queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Consumer вызывает poll в " + Thread.currentThread().getName());
                this.queue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Producer implements Runnable {
    private SimpleBlockingQueue queue;
    public Producer(SimpleBlockingQueue queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        for (int i = 0; i < 6; i++) {
            System.out.println("Offer " + i);
            this.queue.offer(i);
        }
    }
}
