package ru.job4j.chess.figures.black;

import ru.job4j.chess.figures.*;
import java.util.Arrays;
import static java.lang.Math.abs;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class RookBlack implements Figure {

    /** Поле номер позиции фигуры */
    private final Cell position;

    /**
     * Конструктор
     * @param position типа Cell
     */
    public RookBlack(final Cell position) {
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
    public Cell[] way(Cell source, Cell dest) {
        Cell[] steps = new Cell[8];
        int deltaX, deltaY;
        int absDeltaX = abs(dest.x - source.x);
        int absDeltaY = abs(dest.y - source.y);
        if (absDeltaX != 0 & absDeltaY != 0) {
            throw new ImposibleMoveException("Ладья так не ходит");
        }
        deltaY = dest.y - source.y;
        deltaY = (deltaY > 0) ? 1 : ((deltaY < 0) ? -1 : deltaY);
        deltaX = dest.x - source.x;
        deltaX = (deltaX > 0) ? 1 : ((deltaX < 0) ? -1 : deltaX);
        for (int i = 0; i < absDeltaX + absDeltaY; i++) {
            steps[i] = Cell.values()[8 * (source.x + deltaX * (i + 1)) + (source.y + deltaY * (i + 1))];
        }
        return Arrays.copyOf(steps, (absDeltaX + absDeltaY));
    }

    /**
     * Метод устанавливает новую позицию фигуры
     * @param dest типа Cell
     * @return типа Cell
     */
    @Override
    public Figure copy(Cell dest) {
        return new RookBlack(dest);
    }
}
