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

    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        try {
            int index = this.findBy(source);  //находим номер в массиве figures
            Cell[] steps = this.figures[index].way(source, dest);
            if (!steps[steps.length - 1].equals(dest)) {
                throw new ImposibleMoveException("Ошибка движения");
            }
            for (int i = 0; i < steps.length; i++) {
                for (Figure figure : figures) {
                    if (figure.position().equals(steps[i])) {
                        throw new OccupiedWayException("Клетки заняты");
                    }
                }
            }
            rst = true;
            this.figures[index] = this.figures[index].copy(dest);

        } catch (ImposibleMoveException ime) {
            // todo
        } catch (OccupiedWayException owe) {
            // todo
        } catch (FigureNotFoundException fnfe) {
            // todo
        }
        return rst;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
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
