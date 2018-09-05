package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;

public class FunctionDiapason {
    private List<Double> listValue = new ArrayList<>();

    public List<Double> getListValue() {
        return listValue;
    }

    private List<Double> diapason(int start, int end, Function function) {
        List<Double> list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list.add(function.func(i));
        }
        return list;
    }

    public void calcLineaFunc(double a, double b, int start, int end) {
        Function f = (count) -> a * count + b;
        this.listValue = diapason(start, end, f);

    }
    public void calcQuadroFunc(double a, double b, double c, int start, int end) {
        Function f = (count) -> a * count * count + b * count + c;
        this.listValue = diapason(start, end, f);
    }

    public void calcLogFunc(int start, int end) {
        Function f = (count) -> Math.log(count);
        this.listValue = diapason(start, end, f);
    }
}
