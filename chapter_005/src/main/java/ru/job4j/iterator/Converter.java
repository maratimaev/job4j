package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 21.09.2018
 */
public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        List<Integer> list = new ArrayList<>();

        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return false;
            }
            @Override
            public Integer next() {
                it.next();
                return null;
            }
        };
    }
}
