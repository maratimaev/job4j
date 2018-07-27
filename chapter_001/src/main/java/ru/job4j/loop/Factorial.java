package ru.job4j.loop;

public class Factorial {


    public int calc(int n) {
        int fact;
        if (n == 0 || n == 1) {
            return 1;
        }

        fact = calc(n - 1) * n;
        return fact;
    }
}
