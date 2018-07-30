package ru.job4j.array;

public class Matrix {
    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int j = 0; j <= size - 1; j++) {
            for (int i = 0; i <= size - 1; i++) {
                table[i][j] = (i + 1) * (j + 1);
            }
        }
        return table;
    }
}
