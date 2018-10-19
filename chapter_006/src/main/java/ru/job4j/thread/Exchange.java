package ru.job4j.thread;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 19.10.2018
 */
public class Exchange {
    public static void main(String[] args) {
        Store store = new Store();
        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
// Класс Магазин, хранящий произведенные товары
class Store {
    private int product = 0;
    public synchronized void get() { // Без модификатора synchronized product может уменьшатся только локально, дописываться в heap не сразу
        while (product < 1) {
            try {
                wait(); // без wait пришлось бы в цикле опрашивать product и тратить на это ресурсы
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        product--;
        System.out.println("Покупатель купил 1 товар");
        System.out.println("Товаров на складе: " + product);
        notify();
    }
    public synchronized void put() {
        while (product >= 3) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        product++;
        System.out.println("Производитель добавил 1 товар");
        System.out.println("Товаров на складе: " + product);
        notify();
    }
}
// класс Производитель
class Producer implements Runnable {
    Store store;
    Producer(Store store) {
        this.store = store;
    }
    @Override
    public void run() {
        for (int i = 1; i < 6; i++) {
            store.put();
        }
    }
}
// Класс Потребитель
class Consumer implements Runnable {
    Store store;
    Consumer(Store store) {
        this.store = store;
    }
    @Override
    public void run() {
        for (int i = 1; i < 6; i++) {
            store.get();
        }
    }
}
