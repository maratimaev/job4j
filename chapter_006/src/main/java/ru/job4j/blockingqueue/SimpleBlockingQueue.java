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
                System.out.println("offer засыпает в " + Thread.currentThread().getName());
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("В offer " + Thread.currentThread().getName() + " добавляет " + value);
        this.queue.offer(value);
        this.lock = true;
        System.out.println("offer: разбудить всех");
        notifyAll();
    }

    /** Получить элемент, если в очереди есть элементы
     *  Если элементов нет уснуть
     * @return типа Т
     */
    public synchronized T poll() throws InterruptedException {
        T result;
        while (!this.lock) {
            try {
                System.out.println("poll засыпает в " + Thread.currentThread().getName());
                wait();
            } catch (InterruptedException e) {
                throw new InterruptedException();
            }
        }
        result = this.queue.poll();
        System.out.println("В poll " + Thread.currentThread().getName() + " забирает " + result);
        this.lock = false;
        System.out.println("poll: разбудить всех");
        notifyAll();
        return result;
    }

    public synchronized boolean isEmpty() {
        return this.queue.isEmpty();
    }
}