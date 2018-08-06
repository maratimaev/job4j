package ru.job4j.array;

public class Lines {
    public boolean linesMeet(int[] a, int[] b) {
        boolean result = false;
        if (a[0] <= b[1] || b[0] <= a[1]) {
            result = true;
        }
        return result;
    }
}
