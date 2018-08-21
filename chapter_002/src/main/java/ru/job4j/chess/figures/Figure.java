package ru.job4j.chess.figures;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public interface Figure {

    /**
     * Метод возвращает позицию фигуры
     * @return Cell
     */
    Cell position();

    /**
     * Метод возвращает массив позиций передвижения фигуры
     * @param source типа Cell
     * @param dest типа Cell
     * @return типа Cell[]
     */
    Cell[] way(Cell source, Cell dest);

    /**
     * Метод возвращает имя файла
     * @return типа String
     */
    default String icon() {
        return String.format("%s.png", this.getClass().getSimpleName());
    }

    /**
     * Метод устанавливает новую позицию фигуры
     * @param dest типа Cell
     * @return типа Cell
     */
    Figure copy(Cell dest);
}
