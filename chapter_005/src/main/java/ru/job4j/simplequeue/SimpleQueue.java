package ru.job4j.simplequeue;

import ru.job4j.simplestack.SimpleStack;
/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 28.09.2018
 */
public class SimpleQueue<T> {
    /**
     * Стек добавления элементов
     */
    private SimpleStack<T> simpleStack1 = new SimpleStack<>();
    /**
     * Стек преобразования в очередь
     */
    private SimpleStack<T> simpleStack2 = new SimpleStack<>();

    /** Добавление элемента
     * @param value элемент
     */
    public void push(T value) {
        simpleStack1.push(value);
    }

    /** Удаление элемента
     * @return удаленный элемент
     */
    public T poll() {
        T data = simpleStack1.poll();
        while (data != null) {
            simpleStack2.push(data);
            data = simpleStack1.poll();
        }
        return simpleStack2.poll();
    }
}
