package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class LinesTest {
    @Test
    public void whenLinesMeetABReturnTrue() {
        int[] a = {3, 9};
        int[] b = {8, 10};
        Lines line = new Lines();
        boolean result = line.linesMeet(a, b);
        assertThat(result, is(true));
    }
    @Test
    public void whenLinesMeetBAReturnTrue() {
        int[] a = {8, 10};
        int[] b = {3, 9};
        Lines line = new Lines();
        boolean result = line.linesMeet(a, b);
        assertThat(result, is(true));
    }
    @Test
    public void whenLinesMeetBInsideAReturnTrue() {
        int[] a = {3, 10};
        int[] b = {8, 9};
        Lines line = new Lines();
        boolean result = line.linesMeet(a, b);
        assertThat(result, is(true));
    }
    @Test
    public void whenLinesMeetAInsideBReturnTrue() {
        int[] a = {8, 9};
        int[] b = {3, 10};
        Lines line = new Lines();
        boolean result = line.linesMeet(a, b);
        assertThat(result, is(true));
    }
}
