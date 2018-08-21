package ru.job4j.chess.figures.white;

import ru.job4j.chess.figures.*;
import java.util.Arrays;
import static java.lang.Math.abs;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class BishopWhite implements Figure {

    /** Поле номер позиции фигуры */
    private final Cell position;

    /**
     * Конструктор
     * @param position типа Cell
     */
    public BishopWhite(final Cell position) {
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
        Cell[] steps = new Cell[8];
        int deltaX, deltaY;
        int absDeltaX = abs(dest.x - source.x);
        if (absDeltaX != abs(dest.y - source.y)) {
            throw new ImposibleMoveException("Слон так не ходит");
        }
        deltaY = (dest.y - source.y > 0) ? 1 : -1;
        deltaX = (dest.x - source.x > 0) ? 1 : -1;
        for (int i = 0; i < absDeltaX; i++) {
            steps[i] = Cell.values()[8 * (source.x + deltaX * (i + 1)) + (source.y + deltaY * (i + 1))];
        }
        return Arrays.copyOf(steps, absDeltaX);
    }

    /**
     * Метод устанавливает новую позицию фигуры
     * @param dest типа Cell
     * @return типа Cell
     */
    @Override
    public Figure copy(Cell dest) {
        return new BishopWhite(dest);
    }
}

