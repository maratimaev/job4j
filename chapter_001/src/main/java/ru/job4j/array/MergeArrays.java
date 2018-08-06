package ru.job4j.array;

public class MergeArrays {
    public int[] mergeArr(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = a.length - 1, j = b.length - 1, k = result.length;
        while (k > 0) {
            result[--k] = (j < 0 || (i >= 0 && a[i] >= b[j])) ? a[i--] : b[j--];
        }
        return result;
    }
}
