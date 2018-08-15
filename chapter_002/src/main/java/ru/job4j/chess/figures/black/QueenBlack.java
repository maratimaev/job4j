package ru.job4j.chess.figures.black;

import ru.job4j.chess.figures.*;

public class QueenBlack implements Figure{
    private final Cell position;

    public QueenBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        return new Cell[] { dest };
    }

    @Override
    public Figure copy(Cell dest) {
        return new QueenBlack(dest);
    }

}
