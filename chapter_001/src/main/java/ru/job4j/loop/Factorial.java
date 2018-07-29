package ru.job4j.loop;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class Factorial {
    /**
     * Возвращает факториал n.
     * @param n - чило для расчета факториала.
     * @return факториал.
     */
    public int calc(int n) {
        int fact = 1;
        if (n == 0 || n == 1) {
            return 1;
        }

        for (int i = n; i >= 2; i--) {
            fact *= i;
        }
        return fact;

//        fact = calc(n - 1) * n; вариант с рекурсией

    }
}
