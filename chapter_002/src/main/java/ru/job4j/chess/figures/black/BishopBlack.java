package ru.job4j.chess.figures.black;

import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

import static java.lang.Math.abs;

/**
 *
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
    public Cell[] way(Cell source, Cell dest) {
        Cell[] steps = new Cell[8];
        int deltaX, deltaY;
        if (abs(dest.x - source.x) == abs(dest.y - source.y)) {
            deltaX = (dest.x - source.x > 0) ? 1 : -1;
            deltaY = (dest.y - source.y > 0) ? 1 : -1;
            for (int i = 1; i <= abs(dest.x - source.x); i++) {
                steps[i] = Cell.values()[8 * (source.x + deltaX * i) + (source.y + deltaY * i)];
            }
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}

