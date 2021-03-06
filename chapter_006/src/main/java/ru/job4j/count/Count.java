package ru.job4j.count;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 20.10.2018
 */
@ThreadSafe
public class Count {
    @GuardedBy("this")
    private int value;
    public synchronized void increment() {
        this.value++;
    }
    public synchronized int get() {
        return this.value;
    }
}
