package ru.job4j.array;

public class Check {
    public boolean mono(boolean[] data) {
        boolean tmp = data[0];
        for (boolean i : data) {
            if (tmp != i) {
                return false;
            }
        }
        return true;
    }
}
