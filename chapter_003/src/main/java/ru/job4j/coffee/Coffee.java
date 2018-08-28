package ru.job4j.coffee;

import java.util.ArrayList;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class Coffee {
    /**
     * Метод возвращает массив монет.
     * @param value типа int купюра
     * @param price типа int цена кофе
     * @return типа int[]
     */
    int[] changes(int value, int price) {
        ArrayList<Integer> sdacha = new ArrayList<>();
        Coin[] coins = new Coin[]{
                new Coin(10),
                new Coin(5),
                new Coin(2),
                new Coin(1)
        };
        int leftover = value - price;
        for (int i = 0; i < coins.length; i++) {
            coins[i].setNumber(leftover / coins[i].getNominal());
            sdacha.addAll(this.queue(coins[i]));
            leftover = leftover % coins[i].getNominal();
        }
        return this.toIntArray(sdacha);
    }

    /**
     * Метод возвращает список монет одного номинала.
     * @param coin типа Coin
     * @return типа ArrayList<Integer>
     */
    public ArrayList<Integer> queue(Coin coin) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < coin.getNumber(); i++) {
                list.add(coin.getNominal());
        }
        return list;
    }

    /**
     * Метод преобразует ArrayList<Integer> в int[].
     * @param integerList типа ArrayList<Integer>
     * @return типа int[]
     */
    public int[] toIntArray(ArrayList<Integer> integerList) {
        int[] intArray = new int[integerList.size()];
        for (int i = 0; i < integerList.size(); i++) {
            intArray[i] = integerList.get(i);
        }
        return intArray;
    }
}
