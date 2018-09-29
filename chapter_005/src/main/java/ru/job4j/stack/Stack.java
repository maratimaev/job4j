package ru.job4j.stack;

import ru.job4j.dynLinkedList.DynLinkedList;

public class Stack<T> {
    private DynLinkedList<T> dynLinkedList = new DynLinkedList<>();

    public void push(T value) {
        dynLinkedList.add(value);
    }

    public T poll() {
        return dynLinkedList.delete(dynLinkedList.getSize() - 1);
    }
}
