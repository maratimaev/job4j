package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.figures.*;
import ru.job4j.chess.figures.black.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class FiguresTest {

    /**
     *  Метод вызывает исключение неверного хода ладьи
     */
    @Test (expected = ImposibleMoveException.class)
    public void whenRookBlacWrongMove() throws ImposibleMoveException {
        RookBlack rookBlack = new RookBlack(Cell.A8);
        Cell[] steps = new Cell[] {Cell.A7, Cell.A6};
        assertThat(rookBlack.way(Cell.A8, Cell.B6), is(steps));
    }

    /**
     *  Метод проверяет правильность хода пешки
     */
    @Test
    public void whenPawnBlackMove() throws ImposibleMoveException {
        PawnBlack pawnBlack = new PawnBlack(Cell.C7);
        Cell[] steps = new Cell[] {Cell.C6, Cell.C5};
            assertThat(pawnBlack.way(Cell.C7, Cell.C5), is(steps));
    }

    /**
     *  Метод проверяет правильность хода ладьи
     */
    @Test
    public void whenRookBlackMove() throws ImposibleMoveException {
        RookBlack rookBlack = new RookBlack(Cell.A8);
        Cell[] steps = new Cell[] {Cell.A7, Cell.A6};
            assertThat(rookBlack.way(Cell.A8, Cell.A6), is(steps));
    }

    /**
     *  Метод проверяет правильность хода короля
     */
    @Test
    public void whenKingBlackMove() throws ImposibleMoveException {
        KingBlack kingBlack = new KingBlack(Cell.E8);
        Cell[] steps = new Cell[] {Cell.F7};
        assertThat(kingBlack.way(Cell.E8, Cell.F7), is(steps));
    }

    /**
     *  Метод проверяет правильность хода ферьзя
     */
    @Test
    public void whenQueenBlackMove() throws ImposibleMoveException {
        QueenBlack queenBlack = new QueenBlack(Cell.D8);
        Cell[] steps = new Cell[] {Cell.D7, Cell.D6, Cell.D5};
        assertThat(queenBlack.way(Cell.D8, Cell.D5), is(steps));
    }

    /**
     *  Метод проверяет правильность хода коня
     */
    @Test
    public void whenKnightBlackMove() throws ImposibleMoveException {
        KnightBlack knightBlack = new KnightBlack(Cell.B8);
        Cell[] steps = new Cell[] {Cell.C6};
        assertThat(knightBlack.way(Cell.B8, Cell.C6), is(steps));
    }

    /**
     *  Метод проверяет правильность хода слона
     */
    @Test
    public void whenBishopBlackMove() throws ImposibleMoveException {
        BishopBlack bishopBlack = new BishopBlack(Cell.C8);
        Cell[] steps = new Cell[] {Cell.D7, Cell.E6};
        assertThat(bishopBlack.way(Cell.C8, Cell.E6), is(steps));
    }
}
