package ru.job4j.loop;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class Counter {

    /**
     * Возвращает максимальное число.
     * @param start - первое число.
     * @param finish - второе число.
     * @return сумма четных чисел.
     */
    public int add(int start, int finish) {
        int result = 0;

        if ((start & 1) != 0) {
            start += 1;
        }

        for (int i = start; i <= finish; i += 2) {
            result += i;
        }
        return result;
    }
}
