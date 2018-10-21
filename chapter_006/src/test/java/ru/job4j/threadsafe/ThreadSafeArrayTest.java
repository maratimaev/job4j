package ru.job4j.threadsafe;

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 21.10.2018
 */
public class ThreadSafeArrayTest {
    ThreadSafeArray<String> threadSafeArray;

    @Before
    public void SetUp() {
        this.threadSafeArray = new ThreadSafeArray<>();
    }

    @Test
    public void whenAdd2ThreadsThenGetOnyByOne() {
        TestThread t = new TestThread();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("error");
        }

        Iterator it = threadSafeArray.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    class TestThread implements Runnable {
        @Override
        public void run() {
            for(int i = 0; i < 5; i++) {
                threadSafeArray.add("t" + i);
            }
        }
    }
}

