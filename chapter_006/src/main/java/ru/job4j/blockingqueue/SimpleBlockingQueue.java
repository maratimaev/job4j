package ru.job4j.blockingqueue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 23.10.2018
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    /**
     * Поле хранения элементов
     */
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private volatile boolean lock = false;

    /** Добавить элемент если очередь пуста
     *  Если в очереди есть элементы уснуть
     * @param value типа Т
     */
    public synchronized void offer(T value) {
        while (this.lock) {
            try {
                System.out.println("offer sleep");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("in offer " + this.lock + value);
        this.queue.offer(value);
        this.lock = true;
        notify();
    }

    /** Получить элемент, если в очереди есть элементы
     *  Если элементов нет уснуть
     * @return типа Т
     */
    public synchronized T poll() {
        T result;
        while (!this.lock) {
            try {
                System.out.println("poll sleep");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        result = this.queue.poll();
        System.out.println("in poll " + this.lock + result);
        this.lock = false;
        notify();
        return result;
    }
}

/**
 * Нить добавляет элементы
 */
class Producer implements Runnable {
    private SimpleBlockingQueue queue;
    public Producer(SimpleBlockingQueue queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        for (int i = 0; i < 6; i++) {
            this.queue.offer(i);
        }
    }
}

/**
 * Нить удаляет элементы
 */
class Consumer implements Runnable {
    private SimpleBlockingQueue queue;
    public Consumer(SimpleBlockingQueue queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        for (int i = 0; i < 6; i++) {
            this.queue.poll();
        }
    }
}