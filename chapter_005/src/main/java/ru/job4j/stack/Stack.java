package ru.job4j.stack;

import ru.job4j.dynLinkedList.DynLinkedList;

public class Stack<T> {
    private DynLinkedList<T> dynLinkedList;

    public void push(T value) {
        this.dynLinkedList.add(value);
    }

    public T poll() {
        return this.dynLinkedList.delete(0);
    }
}
