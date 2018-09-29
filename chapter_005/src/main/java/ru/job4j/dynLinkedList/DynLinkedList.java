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
        E result = null;
        if (index < this.size) {
            Node<E> node = this.first;
            Node<E> prev = node;
            for (int i = this.size - index - 1; i > 0; i--) {
                prev = node;
                node = node.next;
            }
            if (node.equals(prev)) {
                this.first = node.next;
            } else {
                prev.next = node.next;
            }
            result = node.data;
            this.modCount++;
            this.size--;
        }
        return result;
    }

    /** Получение элемента из списка
     * @param index позиция элемента
     * @return элемент типа Е
     */
    public E get(int index) {
        E result = null;
        if (index < this.size) {
            Node<E> node = this.first;
            for (int i = this.size - 1; i > index; i--) {
                node = node.next;
            }
            result = node.data;
        }
        return result;
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
