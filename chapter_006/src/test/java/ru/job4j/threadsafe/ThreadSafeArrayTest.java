package ru.job4j.threadsafe;

import org.junit.Test;
import java.util.Iterator;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 21.10.2018
 */
public class ThreadSafeArrayTest {

    @Test
    public void whenAdd2ThreadsThenGetOnyByOne() {
        ThreadSafeArray<String> threadSafeArray = new ThreadSafeArray<>();
        TestThread t = new TestThread(threadSafeArray);
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
        StringBuilder str = new StringBuilder();
        while (it.hasNext()) {
            str.append(it.next());
        }
        String result = "t0t1t0t1";
        assertThat(str.toString(), is(result));
    }

    class TestThread implements Runnable {
        private ThreadSafeArray<String> arr;

        public TestThread(ThreadSafeArray<String> arr) {
            this.arr = arr;
        }

        @Override
        public void run() {
            for (int i = 0; i < 2; i++) {
                arr.add("t" + i);
            }
        }
    }
}

