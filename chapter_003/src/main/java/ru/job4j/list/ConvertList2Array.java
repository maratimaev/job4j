package ru.job4j.list;

import static java.lang.Math.*;
import java.util.List;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class ConvertList2Array {

    /**
     * Метод конвертирует список в двумерный массив
     * @param list типа List
     * @param rows типа int
     * @return массив типа int[][]
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int h = -1;
        int cells = (int) ceil(list.size() / (float) rows);
        int[][] array = new int[rows][cells];
        int listSize = list.size();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cells; j++) {
                if ((++h) > listSize - 1) {
                    break;
                } else {
                    array[i][j] = list.get(h);
                }
            }
        }
        return array;

    }
}
