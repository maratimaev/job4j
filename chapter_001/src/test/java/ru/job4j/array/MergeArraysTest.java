package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MergeArraysTest {
    @Test
    public void whenMergeArraysThenSortArray() {
        int[] a = {1, 3, 7};
        int[] b = {2, 5, 6};
        int[] c = {1, 2, 3, 5, 6, 7};
        MergeArrays arr = new MergeArrays();
        int[] result = arr.mergeArr(a, b);
        assertThat(result, is(c));
    }
    @Test
    public void mergeWhenArrayASmaller() {
        int[] a = {1, 3, 5};
        int[] b = {6, 7, 9};
        int[] c = {1, 3, 5, 6, 7, 9};
        MergeArrays arr = new MergeArrays();
        int[] result = arr.mergeArr(a, b);
        assertThat(result, is(c));
    }
    @Test
    public void mergeWhenArrayBSmaller() {
        int[] a = {6, 7, 9};
        int[] b = {1, 3, 5};
        int[] c = {1, 3, 5, 6, 7, 9};
        MergeArrays arr = new MergeArrays();
        int[] result = arr.mergeArr(a, b);
        assertThat(result, is(c));
    }
    @Test
    public void mergeWhenArrayAEmpty() {
        int[] a = {};
        int[] b = {1, 3, 5};
        int[] c = {1, 3, 5};
        MergeArrays arr = new MergeArrays();
        int[] result = arr.mergeArr(a, b);
        assertThat(result, is(c));
    }
    @Test
    public void mergeWhenArrayBEmpty() {
        int[] a = {1, 3, 5};
        int[] b = {};
        int[] c = {1, 3, 5};
        MergeArrays arr = new MergeArrays();
        int[] result = arr.mergeArr(a, b);
        assertThat(result, is(c));
    }
}