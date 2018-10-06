package ru.job4j.simple.set;

import ru.job4j.list.objarray.ObjArrayList;

import java.util.Iterator;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 06.10.2018
 */
public class SimpleSet<E> implements Iterable {
    /**
     * Контейнер для хранения элементов Set на основе динамического массива
     */
    private ObjArrayList<E> objArrayList = new ObjArrayList<>();

    /** Добавление элемента в Set
     * @param e элемент типа Е
     */
    public void add(E e) {
        boolean unique = true;
        for (Object current : objArrayList) {
            if (current.equals(e)) {
                unique = false;
                break;
            }
        }
        if (unique) {
            objArrayList.add(e);
        }
    }

    @Override
    public Iterator iterator() {
        return objArrayList.iterator();
    }
}
