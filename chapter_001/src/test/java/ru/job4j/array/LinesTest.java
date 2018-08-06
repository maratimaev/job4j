package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class LinesTest {
    @Test
    public void whenLinesMeetReturnTrue() {
        int[] a = {3, 9};
        int[] b = {8, 10};
        Lines line = new Lines();
        boolean result = line.linesMeet(a, b);
        assertThat(result, is(true));
    }
}
