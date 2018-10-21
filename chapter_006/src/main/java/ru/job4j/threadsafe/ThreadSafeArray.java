package ru.job4j.threadsafe;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.objarray.ObjArrayList;

import java.util.Iterator;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 21.10.2018
 */
@ThreadSafe
public class ThreadSafeArray<E> implements Iterable {
    @GuardedBy("this")
    ObjArrayList<E> objArrayList;

    public ThreadSafeArray() {
        this.objArrayList = new ObjArrayList<>();
    }

    synchronized public void add(E value) {
        this.objArrayList.add(value);
    }

    synchronized public E get(int index) {
        return this.objArrayList.get(index);
    }

    private ObjArrayList<E> copy(ObjArrayList<E> array) {
        ObjArrayList<E> result = new ObjArrayList<>();
        Iterator<E> iterator = array.iterator();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        return result;
    }

    @Override
    synchronized public Iterator<E> iterator() {
        return copy(this.objArrayList).iterator();
    }
}