package ru.job4j.stack;

import ru.job4j.dynLinkedList.DynLinkedList;
/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 28.09.2018
 */
public class SimpleStack<T> {
    /**
     * Контейнер для хранения элекментов стека
     */
    private DynLinkedList<T> dynLinkedList = new DynLinkedList<>();

    /** Добавление элемента в стек
     * @param value элемент
     */
    public void push(T value) {
        dynLinkedList.add(value);
    }

    /** Удаление элемента из стека
     * @return удаленный элемент
     */
    public T poll() {
        return dynLinkedList.delete(dynLinkedList.getSize() - 1);
    }
}
