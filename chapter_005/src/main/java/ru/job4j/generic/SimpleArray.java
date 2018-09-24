package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 23.09.2018
 */
public class SimpleArray<T> implements Iterable {
    private Object[] objects;
    private int index = 0;

    /** Конструктор
     * @param size размер коллекции
     */
    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    /** Добавить элемент в коллекцию
     * @param model элемент типа Т
     */
    public void add(T model) throws ArrayIndexOutOfBoundsException {
        if (this.index + 1 > objects.length) {
            throw new ArrayIndexOutOfBoundsException("Too many elements");
        }
        this.objects[this.index++] = model;
    }

    /** Заменить элемент в коллекции
     * @param position позиция элемента коллекции типа int
     * @param model элемент типа T
     * @return типа boolean
     */
    public boolean set(int position, T model) {
        boolean result = false;
        if (this.index - position > 0) {
            this.objects[position] = model;
            result = true;
        }
        return result;
    }

    /** Удалить элемент из коллкции
     * @param position позиция элемента коллекции типа int
     * @return типа boolean
     */
    public boolean delete(int position) {
        boolean result = false;
        if (this.index - position > 0) {
            Object[] del = new Object[index];
            System.arraycopy(this.objects, position + 1, del, position, this.index - position - 1);
            this.objects = del;
            index--;
            result = true;
        }
        return result;
    }

    /** Получить элемент коллекции
     * @param position позиция элемента коллекции типа int
     * @return элемент коллекции типа Т
     */
    public T get(int position) throws NoSuchElementException {
        if (this.index - position <= 0) {
            throw new NoSuchElementException("No element");
        }
        return (T) this.objects[position];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int pos = -1;
            @Override
            public boolean hasNext() {
                return pos + 1 < SimpleArray.this.index;
            }

            @Override
            public T next() throws NoSuchElementException {
                T result;
                if (!hasNext()) {
                    throw new NoSuchElementException("No next element");
                }
                result = (T) SimpleArray.this.objects[++pos];
                return result;
            }
        };
    }
}
