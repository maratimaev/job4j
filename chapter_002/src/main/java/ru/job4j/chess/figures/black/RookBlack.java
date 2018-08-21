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
public class RookBlack implements Figure {
    private final Cell position;
    public RookBlack(final Cell position) {
        this.position = position;
    }
    @Override
    public Cell position() {
        return this.position;
    }

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

    @Override
    public Figure copy(Cell dest) {
        return new RookBlack(dest);
    }
}
