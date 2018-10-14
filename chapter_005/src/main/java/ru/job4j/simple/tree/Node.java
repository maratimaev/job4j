package ru.job4j.simple.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 11.10.2018
 */
public class Node<E extends Comparable<E>> {
    /**
     * Дочерние элементы узла
     */
    private final List<Node<E>> children = new ArrayList<>();

    /**
     * Значение узла
     */
    private final E value;

    public Node(final E value) {
        this.value = value;
    }

    public E getValue() {
        return value;
    }

    /** Добавить дочерний узел
     * @param child узел
     */
    public void add(Node<E> child) {
        this.children.add(child);
    }

    /** Вернуть список дочерних узлов
     * @return список узлов
     */
    public List<Node<E>> leaves() {
        return this.children;
    }

    /** Сравнить значения узлов
     * @param that значение узла
     * @return типа boolean
     */
    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;
    }
}
