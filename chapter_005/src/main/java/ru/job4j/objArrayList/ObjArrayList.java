package ru.job4j.objArrayList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 27.09.2018
 */
public class ObjArrayList <E> implements Iterable{
    /**
     * Массив объектов
     */
    Object[] container;
    /**
     * Размер массива Object
     */
    int memSize = 1;
    /**
     * Размер динамического массива
     */
    int listSize = 0;
    /**
     * Счетчик изменений в массиве
     */
    int modCount = 0;
    /**
     * Количество изменений на момент создания итератора
     */
    int expectedModCount;

    public ObjArrayList() {
        this.container = new Object[memSize];
    }

    /** Добавление элемента в массив
     * @param value элемент
     */
    public void add(E value) {
        if (this.listSize >= this.memSize) {
            this.memSize *= 2;
            Object[] cont = new Object[this.memSize];
            System.arraycopy(this.container, 0, cont, 0, this.listSize);
            this.container = cont;
        }
        this.container[this.listSize++] = value;
        this.modCount++;
    }

    /** Получение элемента из массива
     * @param index позициия элемента
     * @return элемент
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        E result = null;
        if (this.listSize - index > 0) {
            result = (E) this.container[index];
        }
        return result;
    }

    @Override
    public Iterator iterator() {
        this.expectedModCount = modCount;

        return new Iterator<E>() {
            int position = -1;
            @Override
            public boolean hasNext() throws ConcurrentModificationException {
                if (ObjArrayList.this.expectedModCount != ObjArrayList.this.modCount) {
                    throw new ConcurrentModificationException();
                }
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
