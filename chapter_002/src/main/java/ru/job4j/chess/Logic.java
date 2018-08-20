package ru.job4j.chess;

import ru.job4j.chess.figures.*;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) throws OccupiedWayException, FigureNotFoundException {
        boolean rst = false;
        try {
            int index = this.findBy(source);
            Cell[] steps = this.figures[index].way(source, dest);
            for (int i = 0; i < steps.length; i++) {
                for (Figure figure : figures) {
                    if (figure.position().equals(steps[i])) {
                        throw new OccupiedWayException("Препятствие");
                    }
                }
            }
            this.figures[index] = this.figures[index].copy(dest);
            rst = true;

        } catch (ImposibleMoveException ime) {
            System.out.println("Ошибка движения");
        } catch (OccupiedWayException owe) {
            System.out.println("Клетки заняты");
        } catch (FigureNotFoundException fnfe) {
            System.out.println("Фигура не найдена");
        }
        return rst;
    }

    public void clean() {
        for (int position = 0; position < this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) throws FigureNotFoundException {
        int rst = -1;
        for (int index = 0; index < this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        if (rst == -1) {
            throw new FigureNotFoundException("Фигура не найдена");
        }
        return rst;
    }
}
