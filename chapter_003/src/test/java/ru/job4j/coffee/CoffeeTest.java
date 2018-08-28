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
    public void when50Then7() {
        Coffee coffeeMachine = new Coffee();
        int[] result = coffeeMachine.changes(100, 42);
        int[] expect = {10, 10, 10, 10, 10, 5, 2, 1};
        assertThat(result, is(expect));
    }
}
