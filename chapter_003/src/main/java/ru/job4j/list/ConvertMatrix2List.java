package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class ConvertMatrix2List {

    /**
     * Метод конвертирует двумерный массив в список
     * @param array типа int[][]
     * @return список типа List<Integer>
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] row: array) {
            for (int cell: row) {
                list.add(cell);
            }
        }
        return list;
    }
}
