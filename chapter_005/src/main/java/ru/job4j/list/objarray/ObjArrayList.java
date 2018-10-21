package ru.job4j.list.objarray;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 27.09.2018
 */
public class ObjArrayList<E> implements Iterable {
    /**
     * Массив объектов
     */
    private Object[] container;
    /**
     * Размер массива Object
     */
    private int memSize = 1;
    /**
     * Размер динамического массива
     */
    private int listSize;
    /**
     * Счетчик изменений в массиве
     */
    private int modCount;
    /**
     * Количество изменений на момент создания итератора
     */
    private int expectedModCount;

    public ObjArrayList() {
        container = new Object[memSize];
    }

    /**
     * Увеличение размера массива
     */
    private void extendArray() {
        memSize *= 2;
        Object[] cont = new Object[memSize];
        System.arraycopy(container, 0, cont, 0, listSize);
        container = cont;
    }

    /** Добавление элемента в массив
     * @param value элемент
     */
    public void add(E value) {
        if (listSize >= memSize) {
            extendArray();
        }
        container[listSize++] = value;
        modCount++;
    }

    /** Получение элемента из массива
     * @param index позициия элемента
     * @return элемент
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        E result = null;
        if (listSize - index > 0) {
            result = (E) container[index];
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        expectedModCount = modCount;

        return new Iterator<E>() {
            int position = -1;
            @Override
            public boolean hasNext() {
                return position + 1 < ObjArrayList.this.listSize;
            }

            @SuppressWarnings("unchecked")
            @Override
            public E next() throws NoSuchElementException, ConcurrentModificationException {
                if (ObjArrayList.this.expectedModCount != ObjArrayList.this.modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException("No next element");
                }
                return (E) ObjArrayList.this.container[++position];
            }
        };
    }
}
