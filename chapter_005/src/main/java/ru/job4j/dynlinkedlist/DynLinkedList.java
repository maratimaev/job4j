package ru.job4j.dynlinkedlist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 28.09.2018
 */
public class DynLinkedList<E> implements Iterable<E> {

    /**
     * Первый элемент в списке
     */
    private Node<E> first;
    /**
     * Последний элемент в списке
     */
    private Node<E> last;
    /**
     * Размер списка
     */
    private int size;
    /**
     * Счетчик изменений в массиве
     */
    private int modCount;

    /** Добавление элемента в список
     * @param data элемент типа Е
     */
    public void add(E data) {
        Node<E> newLink = new Node<>(data);
        if (first == null) {
            first = newLink;
            last = newLink;
        } else {
            last.next = newLink;
            newLink.prev = last;
            last = newLink;
        }
        size++;
        modCount++;
    }

    /** Удаление элемента по индексу
     * @param index элемента типа int
     * @return удаленный элемент
     */
    public E delete(int index) {
        E result = null;
        if ((size > index) && (index >= 0)) {
            Node<E> node = first;
            Node<E> previous = first;
            for (int i = 0; i < index; i++) {
                previous = node;
                node = node.next;
            }
            if (node.equals(previous)) {
                first = node.next;
                if (node.next != null) {
                    first.prev = null;
                }
            } else {
                previous.next = node.next;
                if (node.next != null) {
                    node.next.prev = previous;
                }
            }
            result = node.data;
            modCount++;
            size--;
        }
        return result;
    }


    /** Получение элемента из списка
     * @param index позиция элемента
     * @return элемент типа Е
     */
    public E get(int index) {
        E result = null;
        if ((size > index) && (index >= 0)) {
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
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
        Node<E> next;
        Node<E> prev;

        Node(E data) {
            this.data = data;
        }
    }

    @Override
    public Iterator<E> iterator() {
        int expectedModCount = modCount;

        return new Iterator<E>() {
            int position;
            Node<E> node = DynLinkedList.this.first;

            @Override
            public boolean hasNext() {
                return position < DynLinkedList.this.getSize();
            }

            @Override
            public E next() throws NoSuchElementException, ConcurrentModificationException {
                if (expectedModCount != DynLinkedList.this.modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException("No next element");
                }
                E data = node.data;
                node = node.next;
                position++;
                return data;
            }
        };
    }
}
