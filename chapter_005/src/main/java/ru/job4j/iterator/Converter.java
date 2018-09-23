package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 21.09.2018
 */
public class Converter {
    Iterator<Integer> it;
    Iterator<Integer> convert(Iterator<Iterator<Integer>> itOfIt) {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                boolean result = false;
                while (itOfIt.hasNext() || (it != null && it.hasNext())) {
                    if ((it == null & itOfIt.hasNext()) || (it != null && (!it.hasNext() & itOfIt.hasNext()))) {
                        it = itOfIt.next();
                    }
                    if (it != null && it.hasNext()) {
                        result = true;
                        break;
                    }
                }
                return result;
            }
            @Override
            public Integer next() throws NoSuchElementException {
               if (!hasNext()) {
                   throw new NoSuchElementException();
               }
               return it.next();
            }
        };
    }
}
