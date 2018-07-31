package ru.job4j.array;

public class MatrixCheck {
    public boolean mono(boolean[][] data) {
        boolean result = true;
        boolean left = data[0][0];
        boolean right = data[0][data.length - 1];
        for (int i = 0; i <= data.length - 1; i++) {
            if ((data[i][i] != left) || (data[i][data.length - 1 - i] != right)) {
                result = false;
                break;
            }
        }
        return result;
    }
}
