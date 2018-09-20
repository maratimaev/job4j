package ru.job4j;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 20.09.2018
 */
public class MatrixIterator implements Iterator {

    private final int[][] values;
    private int indexRow = 0;
    private int indexCell = 0;

    public MatrixIterator(int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return this.values.length > this.indexRow ;
    }

    @Override
    public Object next() throws NoSuchElementException {
        if (this.values.length <= this.indexRow) {
            throw new NoSuchElementException("No next element");
        }

        int result = this.values[this.indexRow][this.indexCell];
        if (++this.indexCell >= this.values[this.indexRow].length) {
            this.indexCell = 0;
            this.indexRow++;
        }
        return result;
    }
}
