package ru.job4j.lambda;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */

@FunctionalInterface
interface Function {
    double func(int count);
}
