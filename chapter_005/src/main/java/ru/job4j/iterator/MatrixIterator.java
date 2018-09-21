package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Реализация итератора перебора двумерного массива
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 20.09.2018
 */
public class MatrixIterator implements Iterator {

    /* Входной массив */
    private final int[][] values;
    /* Индекс одномерного массива */
    private int indexRow = 0;
    /* Индекс элемента в в одномерном массиве */
    private int indexCell = -1;

    public MatrixIterator(int[][] values) {
        this.values = values;
    }

    /**
     * Метод проверяет слуществование следующего элемента в масстве
     * @return  boolean
     */
    @Override
    public boolean hasNext() {
        return !((this.indexCell + 1 >= this.values[this.indexRow].length) & (this.indexRow + 1 >= this.values.length));
    }

    /**
     * Метод передвигает каретку на следующий элемент и возвращает его значение
     * @return элемент массива типа Object[]
     */
    @Override
    public Object next() throws NoSuchElementException {
        if (this.indexRow >= this.values.length) {
            throw new NoSuchElementException("No next element");
        }

        if (++this.indexCell >= this.values[this.indexRow].length) {
            this.indexCell = 0;
            this.indexRow++;
        }

        return this.values[this.indexRow][this.indexCell];
    }
}
