package ru.job4j.simple.tree;

import java.util.*;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 14.10.2018
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    /**
     * Корень дерева
     */
    private Node<E> root;

    public Tree(E root) {
        this.root = new Node<>(root);
    }

    /** Добавить узед в дерево
     * @param parent родительский узел
     * @param child дочерний узел
     * @return типа boolean
     */
    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> node = findBy(parent);
        node.ifPresent(p -> p.add(new Node<>(child)));
        return node.isPresent();
    }

    /** Поиск узла в ширину
     * @param value значение узла
     * @return найденный узел
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return !data.isEmpty();
            }

            @Override
            public E next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException("No next element");
                }
                Node<E> that = data.poll();
                for (Node<E> child : that.leaves()) {
                    data.offer(child);
                }
                return that.getValue();
            }
        };
    }
}
