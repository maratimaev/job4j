package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class ConvertList {
    /**
     * Метод конвертирует список массивов в список
     * @param list типа List<int[]>
     * @return список типа List<Integer>
     */
    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
            for (int[] current: list) {
                for (int i: current) {
                    result.add(i);
                }
            }
        return result;
    }
    /**
     * Метод конвертирует список массивов в список используя Stream API
     * @param list типа List<int[]>
     * @return список типа List<Integer>
     */
    public List<Integer> convertStream(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        list.forEach(array -> IntStream.of(array).forEach(result::add));
        return result;
    }
}
