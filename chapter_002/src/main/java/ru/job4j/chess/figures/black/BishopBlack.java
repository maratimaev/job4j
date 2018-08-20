package ru.job4j.chess.figures.black;

import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;
import ru.job4j.chess.figures.ImposibleMoveException;

import java.util.Arrays;

import static java.lang.Math.abs;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

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

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}

