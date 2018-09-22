package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 22.09.2018
 */
public class Converter1 {
    Iterator<Integer> it;
    private int indexIt = 0;
    private int indexInIt = 0;



    Iterator<Integer> convert(Iterator<Iterator<Integer>> itOfIt) {
        List<Iterator<Integer>> list = new ArrayList<>();
        List<Integer> l = new ArrayList<>();
        itOfIt.forEachRemaining(list::add);
        for (Iterator<Integer> i : list) {
            if (i.hasNext()) {
                l.add(i.next());
            }
        }


        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                boolean i = list.get(indexIt).hasNext();
                return (indexIt + 1 < list.size()) || (list.get(indexIt).hasNext());
            }
            @Override
            public Integer next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (indexIt + 1 <= list.size() & !list.get(indexIt).hasNext()) {
                    indexIt++;
                }
                return list.get(indexIt).next();
            }
        };
    }
}
