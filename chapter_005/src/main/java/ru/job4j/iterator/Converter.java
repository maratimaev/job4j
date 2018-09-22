package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 21.09.2018
 */
public class Converter {
    private int index = -1;

    Iterator<Integer> convert(Iterator<Iterator<Integer>> itOfIt) {
        List<Iterator<Integer>> listOfIt = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        itOfIt.forEachRemaining(listOfIt::add);
        for (Iterator<Integer> i : listOfIt) {
            while (i.hasNext()) {
                list.add(i.next());
            }
        }

        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return index + 1 < list.size();
            }
            @Override
            public Integer next() throws NoSuchElementException {
               if (!hasNext()) {
                   throw new NoSuchElementException();
               }
               return list.get(++index);
            }
        };
    }
}
