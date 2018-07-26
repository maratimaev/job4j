package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test max.
 *
 * @author Marat Imaev (imaevmarat@outlook.com)
 */

public class MaxTest {
    @Test
    public void whenFirstLessSecond() {
        Max maxim = new Max();
        int result = maxim.max(2, 1);
        assertThat(result, is(2));
    }

    @Test
    public void when3IsMaxfrom132() {
        Max maxim = new Max();
        int result = maxim.max(1, 3, 2);
        assertThat(result, is(3));
    }
}
