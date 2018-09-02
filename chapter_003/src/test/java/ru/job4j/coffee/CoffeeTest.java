package ru.job4j.coffee;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class CoffeeTest {
    /* Тест проверяет выдачу сдачи */
    @Test
    public void when100Then8() {
        Coffee coffeeMachine = new Coffee();
        int[] result = coffeeMachine.changes(100, 42);
        int[] expect = {10, 10, 10, 10, 10, 5, 2, 1};
        assertThat(result, is(expect));
    }
    /* Тест проверяет недостаток денег */
    @Test
    public void when40ThenNothing() {
        Coffee coffeeMachine = new Coffee();
        int[] result = coffeeMachine.changes(40, 45);
        assertThat(result.length, is(0));
    }
    /* Тест проверяет отсутствие сдачи */
    @Test
    public void when40Then0() {
        Coffee coffeeMachine = new Coffee();
        int[] result = coffeeMachine.changes(40, 40);
        assertThat(result.length, is(0));
    }
    /* Тест проверяет выдачу мелочи */
    @Test
    public void when47Then2() {
        Coffee coffeeMachine = new Coffee();
        int[] result = coffeeMachine.changes(50, 47);
        int[] expect = new int[]{2, 1};
        assertThat(result, is(expect));
    }
}
