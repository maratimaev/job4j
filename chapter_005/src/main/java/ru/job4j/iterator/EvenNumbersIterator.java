package ru.job4j.iterator;

import java.util.Iterator;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 21.09.2018
 */
public class EvenNumbersIterator implements Iterator {

    private final int[] values;

    public EvenNumbersIterator(int[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Object next() {

        return null;
    }
}
