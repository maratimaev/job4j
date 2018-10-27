package ru.job4j.cache;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 27.10.2018
 */
public class OptimisticException extends RuntimeException {
    public OptimisticException(String str) {
        super(str);
    }
}
