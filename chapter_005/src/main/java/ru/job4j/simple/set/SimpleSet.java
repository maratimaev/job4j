package ru.job4j.simple.set;

import ru.job4j.list.objarray.ObjArrayList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 06.10.2018
 */
public class SimpleSet<E> {

    private ObjArrayList<E> objArrayList = new ObjArrayList<>();

    public void add(E e) {

        objArrayList.add(e);
    }

}
