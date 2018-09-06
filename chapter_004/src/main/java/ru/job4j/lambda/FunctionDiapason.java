package ru.job4j.lambda;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class FunctionDiapason {
    private List<Double> listValue = new ArrayList<>();

    public List<Double> getListValue() {
        return listValue;
    }
    /**
     * Метод возвращает список значений принимаемой функции
     * @param start типа int начало диапазона
     * @param end типа int конец дипазона
     * @param function лямбда функция
     * @return типа List<Double>
     */
    private List<Double> diapason(int start, int end, Function function) {
        List<Double> list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list.add(function.func(i));
        }
        return list;
    }
    /**
     * Метод вычисляет линейную функцию
     * @param a типа double первый коэффициент
     * @param b типа double второй коэффициент
     * @param start типа int начало диапазона
     * @param end типа int конец дипазона
     */
    public void calcLineaFunc(double a, double b, int start, int end) {
        Function f = (count) -> a * count + b;
        this.listValue = diapason(start, end, f);

    }
    /**
     * Метод вычисляет квадратичную функцию
     * @param a типа double первый коэффициент
     * @param b типа double второй коэффициент
     * @param c типа double третий коэффициент
     * @param start типа int начало диапазона
     * @param end типа int конец дипазона
     */
    public void calcQuadroFunc(double a, double b, double c, int start, int end) {
        Function f = (count) -> a * count * count + b * count + c;
        this.listValue = diapason(start, end, f);
    }
    /**
     * Метод вычисляет логарифмическую функцию с округлением до 1 знака
     * @param start типа int начало диапазона
     * @param end типа int конец дипазона
     */
    public void calcLogFunc(int start, int end) {
        Function f = (count) -> (new BigDecimal(Math.log(count)).setScale(1, RoundingMode.HALF_EVEN).doubleValue());
        this.listValue = diapason(start, end, f);
    }
}
