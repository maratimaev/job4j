package ru.job4j.chess.figures;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class OccupiedWayException extends Exception {
    public OccupiedWayException(String msg) {
        System.out.println(msg);
    }
}
