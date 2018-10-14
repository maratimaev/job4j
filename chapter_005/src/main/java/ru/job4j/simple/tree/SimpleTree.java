package ru.job4j.simple.tree;

import java.util.Optional;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 11.10.2018
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * Добавить элемент child в parent.
     * Parent может иметь список child.
     * @param parent parent.
     * @param child child.
     * @return резултат типа boolean
     */
    boolean add(E parent, E child);

    /** Алгоритм поиска узла в ширину
     * @param value значение узла
     * @return узел
     */
    Optional<Node<E>> findBy(E value);
}
