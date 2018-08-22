package ru.job4j.chess.figures.black;

import ru.job4j.chess.figures.*;
import java.util.Arrays;
import static java.lang.Math.abs;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class KingBlack implements Figure {

    /** Поле номер позиции фигуры */
    private final Cell position;

    /**
     * Конструктор
     * @param position типа Cell
     */
    public KingBlack(final Cell position) {
        this.position = position;
    }

    /**
     * Метод возвращает позицию фигуры
     * @return Cell
     */
    @Override
    public Cell position() {
        return this.position;
    }

    /**
     * Метод возвращает массив позиций передвижения фигуры
     * @param source типа Cell
     * @param dest типа Cell
     * @return типа Cell[]
     */
    @Override
    public Cell[] way(Cell source, Cell dest) throws ImposibleMoveException {
        Cell[] steps = new Cell[1];
        int deltaX, deltaY;
        int absDeltaX = abs(dest.x - source.x);
        int absDeltaY = abs(dest.y - source.y);
        if (absDeltaX > 1 || absDeltaY > 1) {
            throw new ImposibleMoveException("Король так не ходит");
        }
        deltaY = dest.y - source.y;
        deltaY = (deltaY > 0) ? 1 : ((deltaY < 0) ? -1 : deltaY);
        deltaX = dest.x - source.x;
        deltaX = (deltaX > 0) ? 1 : ((deltaX < 0) ? -1 : deltaX);
        steps[0] = Cell.values()[8 * (source.x + deltaX) + (source.y + deltaY)];
        return Arrays.copyOf(steps, 1);
    }

    /**
     * Метод устанавливает новую позицию фигуры
     * @param dest типа Cell
     * @return типа Cell
     */
    @Override
    public Figure copy(Cell dest) {
        return new KingBlack(dest);
    }
}
