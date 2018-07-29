package ru.job4j.loop;

import java.util.function.BiPredicate;
/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class Paint {
    /**
     * Возвращает строку - правую стороны пирамиды.
     * @param height - высота пирамиды.
     * @return строка с разделителями.
     */
    public String rightTrl(int height) {
        return this.loopBy(height, height, (row, column) -> row >= column);
    }

    /**
     * Возвращает строку - левую стороны пирамиды.
     * @param height - высота пирамиды.
     * @return строка с разделителями.
     */
    public String leftTrl(int height) {
        return this.loopBy(height, height, (row, column) -> row >= height - column - 1);
    }

    /**
     * Возвращает строку - пирамиду.
     * @param height - высота пирамиды.
     * @return строка с разделителями.
     */
    public String pyramid(int height) {
        return this.loopBy(height,2 * height - 1,
                (row, column) -> row >= height - column - 1 && row + height - 1 >= column);
    }

    /**
     * Возвращает строку.
     * @param height - высота пирамиды.
     * @param weight - ширина пирамиды.
     * @param predict - лямбда выражение.
     * @return строка с разделителями.
     */
    private String loopBy(int height, int weight, BiPredicate<Integer, Integer> predict) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (predict.test(row, column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }

}

