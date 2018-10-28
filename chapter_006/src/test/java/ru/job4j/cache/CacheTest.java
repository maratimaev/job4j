package ru.job4j.cache;

import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 27.10.2018
 */
public class CacheTest {
    @Test
    public void whenCreateBlockingThreadsCatchException() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Cache cache = new Cache();
        Cache.Base model = cache.new Base(1, "test");
        cache.add(model);
        CatchUpdater catchUpdater = new CatchUpdater(ex, cache, model);

        Thread one = new Thread(catchUpdater);
        Thread two = new Thread(catchUpdater);
        one.start();
        two.start();
        one.join();
        two.join();
        assertThat(ex.get().getMessage(), is("Нарушение доступа"));
    }

    class CatchUpdater implements Runnable {
        AtomicReference<Exception> ex;
        Cache cache;
        Cache.Base model;

        public CatchUpdater(AtomicReference<Exception> ex, Cache cache, Cache.Base model) {
            this.ex = ex;
            this.cache = cache;
            this.model = model;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 50000; i++) {
                    model.setName("test" + i);
                    cache.update(model);
                }
            } catch (Exception e) {
                ex.set(e);
            }
        }
    }

}
