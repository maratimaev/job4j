package ru.job4j.chess.figures;

public class ImposibleMoveException extends RuntimeException {
    public ImposibleMoveException(String msg) {
        super(msg);
    }

}
