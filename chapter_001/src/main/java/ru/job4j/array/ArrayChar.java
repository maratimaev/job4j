package ru.job4j.array;

/**
 * Обертка над строкой.
 */
public class ArrayChar {
    private char[] data;

    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * Проверяет. что слово начинается с префикса.
     * @param prefix префикс.
     * @return true если слово начинаеться с префикса
     */
    public boolean startWith(String prefix) {
        char[] value = prefix.toCharArray();
        for (int i = 0; i <= value.length - 1; i++) {
            if (value[i] != data[i]) {
                return false;
            }
        }
        return true;
    }
}
