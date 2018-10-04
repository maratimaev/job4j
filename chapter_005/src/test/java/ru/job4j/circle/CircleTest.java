package ru.job4j.circle;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 30.09.2018
 */
public class CircleTest {
    private Circle<String> circle = new Circle<>();

    @Before
    public void setUp() {
        circle.add("test");
        circle.add("test1");
        circle.add("test2");
        circle.add("test3");
        circle.add("test4");
        circle.add("test5");
    }

    @Test
    public void whenCircleIsClosedInTheMiddleThenReturnTrue() {
        circle.link(circle.get(3), circle.get(2));
        assertThat(circle.hasCircle(circle.get(0)), is(true));
    }

    @Test
    public void whenCircleIsClosedToRingThenReturnTrue() {
        circle.link(circle.get(5), circle.get(0));
        assertThat(circle.hasCircle(circle.get(0)), is(true));
    }

    @Test
    public void whenCircleIsNotClosedThenReturnFalse() {
        assertThat(circle.hasCircle(circle.get(0)), is(false));
    }
}
