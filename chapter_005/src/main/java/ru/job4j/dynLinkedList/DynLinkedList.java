package ru.job4j.dynLinkedList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 28.09.2018
 */
public class DynLinkedList<E> implements Iterable<E>{

    /**
     * Первый элемент в списке
     */
    private Node<E> first;
    /**
     * Размер списка
     */
    private int size;
    /**
     * Счетчик изменений в массиве
     */
    private int modCount;
    /**
     * Количество изменений на момент создания итератора
     */
    private int expectedModCount;

    /** Добавление элемента в список
     * @param data элемент типа Е
     */
    public void add(E data) {
        Node<E> newLink = new Node<>(data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
        this.modCount++;
    }

    /** Удаление элемента из списка
     * @param index индекс элемента типа int
     * @return элемент типа Е
     */
    public E delete(int index) {
        Node<E> result = this.first;
        Node<E> prev = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        prev.next = result.next;
        this.modCount++;
        return result.data;
    }

    /** Получение элемента из списка
     * @param index позиция элемента
     * @return элемент типа Е
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /** Получение размера списка
     * @return размер списка
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {
        E data;
        DynLinkedList.Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    @Override
    public Iterator<E> iterator() {
        this.expectedModCount = modCount;

        return new Iterator<E>() {
            int position = -1;
            boolean isFirst = true;
            Node<E> itres = DynLinkedList.this.first;

            @Override
            public boolean hasNext() throws ConcurrentModificationException {
                if (DynLinkedList.this.expectedModCount != DynLinkedList.this.modCount) {
                    throw new ConcurrentModificationException();
                }
                return position + 1 < DynLinkedList.this.getSize();
            }

            @Override
            public E next() throws NoSuchElementException {
                E data;
                if (!hasNext()) {
                    throw new NoSuchElementException("No next element");
                }
                if (isFirst) {
                    data = itres.data;
                    isFirst = false;
                } else {
                    itres = itres.next;
                    data = itres.data;
                }
                position++;
                return data;
            }
        };
    }
}
