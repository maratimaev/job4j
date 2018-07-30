package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BubbleSortTest {
    @Test
    public void whenSortArrayWithTenElementsThenSortedArray() {
        int[] input = {1, 5, 4, 2, 3, 1, 7, 8, 0, 5};
        int[] output = {0, 1, 1, 2, 3, 4, 5, 5, 7, 8};
        BubbleSort arr = new BubbleSort();
        int[] result = arr.sort(input);
        assertThat(result, is(output));
    }
}