    package ru.job4j.array;

    import java.util.Arrays;

    public class ArrayDuplicate {
        public String[] remove(String[] array) {
            int arrend = array.length;
            for (int i = 0; i < arrend; i++) {
                for (int j = i + 1; j < arrend; j++) {
                    if (array[i].equals(array[j])) {
                        array[j] = array[arrend - 1];
                        arrend--;
                        j--;
                    }
                }
            }
            return Arrays.copyOf(array, arrend);
        }
    }
