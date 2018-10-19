package ru.job4j.thread;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 19.10.2018
 */
public class NoVisibility extends Thread {
    //volatile
    boolean keepRunning = true;
    volatile int iVolatile;
    public static void main(String[] args) throws InterruptedException {
        NoVisibility t = new NoVisibility();
        t.start();
        Thread.sleep(1000);
        t.keepRunning = false;
        System.out.println("keepRunning is false");
    }
    @Override
    public void run() {
        int x = 10;
        while (keepRunning) {
            // Поток занят из-за цикла и JVM решает, что у потока нет обновлений для локальных переменных
            //
            // Исправить ситуацию может
            // добавление volatile перед keepRunning,
            // добавление любого synchronized метода,
            // volatile переменных
            // или просто любого дополнительного кода
            //
            // iVolatile++;
            // System.out.println("");
            x++;
        }
        System.out.println("x:" + x);
    }
}

