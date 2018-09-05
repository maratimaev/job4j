package ru.job4j.lambda;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FunctionTest {
    /**
     * Тест проверяет создание пользователя
     */
    @Test
    public void calcLineaFunctionTest() {
        ArrayList<Double> result = new ArrayList<Double>();
        Double[] toList = {5.0, 7.0, 9.0, 11.0, 13.0};
        FunctionDiapason functionDiapason = new FunctionDiapason();
        functionDiapason.calcLineaFunc(2, 3, 1, 5);
        result.addAll(Arrays.asList(toList));
        assertThat(functionDiapason.getListValue(), is(result));
    }
}
