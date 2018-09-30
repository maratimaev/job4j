package ru.job4j.circle;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 30.09.2018
 */
public class Circle<T> {
    /**
     * Крайний элемент списка
     */
    private Node<T> first;
    /**
     * Размер списка
     */
    private int size;

    /** Добавление элемента в список
     * @param data элемент типа T
     */
    public void add(T data) {
        Node<T> newLink = new Node<>(data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /** Получение элемента из списка
     * @param index позиция элемента
     * @return элемент типа Node<T>
     */
    public Node<T> get(int index) {
        Node<T> result = null;
        if ((this.size > index) & (index >= 0)) {
            Node<T> node = this.first;
            for (int i = this.size - 1; i > index; i--) {
                node = node.next;
            }
            result = node;
        }
        return result;
    }

    /** Проверка на замкнутость списка
     * @param first с какого элемента начинать проверку
     * @return замкнут или нет
     */
    public boolean hasCircle(Node<T> first) {
        Node<T> slow = first;
        Node<T> fast = first.next;
        boolean result = true;

        while (!slow.equals(fast)) {
            if (fast == null || fast.next == null) {
                result = false;
                break;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return result;
    }

    /** Устанавливает связь между элементами списка
     * @param n1 первый элемент ссылается
     * @param n2 на второй элемент
     */
    public void link(Node<T> n1, Node<T> n2) {
        n1.next = n2;
    }

    private static class Node<T> {
        T data;
        Node<T> next;
        Node(T data) {
            this.data = data;
        }
    }
}
