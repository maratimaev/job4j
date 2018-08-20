package ru.job4j.chess.figures;

public interface Figure {
    Cell position(); //текущая клетка

    Cell[] way(Cell source, Cell dest); //массив возможных путей

    default String icon() {
        return String.format("%s.png", this.getClass().getSimpleName()); //отрисовка фигуры?
    }

    Figure copy(Cell dest);
}
