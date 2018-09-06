package ru.job4j.lambda;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class FunctionTest {
    /**
     * Тест проверяет вычисление линейной функции
     */
    @Test
    public void calcLineaFunctionTest() {
        Double[] toList = {5.0, 7.0, 9.0, 11.0, 13.0};
        FunctionDiapason functionDiapason = new FunctionDiapason();
        functionDiapason.calcLineaFunc(2, 3, 1, 5);
        List<Double> result = new ArrayList<>(Arrays.asList(toList));
        assertThat(functionDiapason.getListValue(), is(result));
    }

    /**
     * Тест проверяет вычисление квадратичной функции
     */
    @Test
    public void calcQuadroFunctionTest() {
        Double[] toList = {9.0, 18.0, 31.0, 48.0, 69.0};
        FunctionDiapason functionDiapason = new FunctionDiapason();
        functionDiapason.calcQuadroFunc(2, 3, 4, 1, 5);
        List<Double> result = new ArrayList<>(Arrays.asList(toList));
        assertThat(functionDiapason.getListValue(), is(result));
    }
    /**
     * Тест проверяет вычисление логарифмической функции
     */
    @Test
    public void calcLogFunctionTest() {
        Double[] toList = {0.0, 0.7, 1.1, 1.4, 1.6};
        FunctionDiapason functionDiapason = new FunctionDiapason();
        functionDiapason.calcLogFunc(1, 5);
        List<Double> result = new ArrayList<>(Arrays.asList(toList));
        assertThat(functionDiapason.getListValue(), is(result));
    }
}
