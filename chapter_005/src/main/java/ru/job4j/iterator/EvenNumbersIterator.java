package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Реализация итератора перебора четных элементов массива
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 21.09.2018
 */
public class EvenNumbersIterator implements Iterator {
    /* Входной массив */
    private final int[] values;
    /* Индекс одномерного массива */
    private int index = -1;

    public EvenNumbersIterator(int[] values) {
        this.values = values;
    }

    /**
     * Метод проверяет слуществование следующего четного элемента в массиве
     * @return  boolean
     */
    @Override
    public boolean hasNext() {
        return evenNextIndex() < values.length;
    }

    /**
     * Метод возвращает индекс в массиве следующего четного элемента
     * @return индекс элемента массива типа int
     */
    private int evenNextIndex() {
        int i = index;
        while (++i < values.length) {
            if (values[i] % 2 == 0) {
                break;
            }
        }
        return i;
    }

    /**
     * Метод передвигает каретку на следующий четный элемент и возвращает его значение
     * @return элемент массива типа Object[]
     */
    @Override
    public Object next() throws NoSuchElementException {
        index = evenNextIndex();
        if (index >= values.length) {
            throw new NoSuchElementException("No even element");
        }
        return values[index];
    }
}
