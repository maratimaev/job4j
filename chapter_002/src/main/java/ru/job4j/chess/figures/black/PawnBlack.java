package ru.job4j.chess.figures.black;

import ru.job4j.chess.figures.*;

import java.util.Arrays;

import static java.lang.Math.abs;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PawnBlack implements Figure {
    private final Cell position;

    public PawnBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        Cell[] steps = new Cell[2];
        int deltaX, deltaY;
        int absDeltaY = abs(dest.y - source.y);
        if (!((dest.x == source.x) & ((source.y == dest.y + 1) || ((source.y == 6) & (source.y == dest.y + 2))))) {
            throw new ImposibleMoveException("Пешка так не ходит");
        }
        deltaY = -1;
        deltaX = 0;
        for (int i = 0; i < absDeltaY; i++) {
            steps[i] = Cell.values()[8 * (source.x + deltaX * (i + 1)) + (source.y + deltaY * (i + 1))];
        }
        return Arrays.copyOf(steps, absDeltaY);
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnBlack(dest);
    }
}
