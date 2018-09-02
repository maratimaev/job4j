package ru.job4j.coffee;

import java.util.Arrays;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class Coffee {
    /**
     * Метод возвращает массив монет.
     *
     * @param value типа int купюра
     * @param price типа int цена кофе
     * @return типа int[]
     */
    int[] changes(int value, int price) {
        int[] nominal = {10, 5, 2, 1};
        int[] coins = new int[100];
        int count = 0;
        int leftover = value - price;
        for (int i = 0; i < nominal.length; i++) {
            for (int j = 0; j < leftover / nominal[i]; j++) {
                coins[count++] = nominal[i];
            }
            leftover = leftover % nominal[i];
        }
        return Arrays.copyOf(coins, count);
    }
}