package ru.job4j.loop;


/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class Board {

    /**
     * Возвращает строку - шахматную доску.
     * @param width - ширина доски.
     * @param height - высота доски.
     * @return строка с разделителями.
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                if (((i + j) & 1) == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }

            screen.append(ln);
        }
        return screen.toString();
    }
}
