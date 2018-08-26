package ru.job4j.search;

import java.util.LinkedList;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class PriorityQueue {
    /** Поле список задач  */
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод вставляет в нужную позицию элемент.
     * Позиция определяется по полю приоритет.
     * @param task задача
     */
    public void put(Task task) {
        int index = tasks.size();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getPriority() > task.getPriority()) {
                index = i;
                break;
            }
        }
        this.tasks.add(index, task);
    }

    /**
     * Метод возвращает первую задачу из списка
     * Задача удаляется
     * @return типа Task
     */
    public Task take() {
        return this.tasks.poll();
    }
}
