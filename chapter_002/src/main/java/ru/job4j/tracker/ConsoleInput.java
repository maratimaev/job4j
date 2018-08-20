package ru.job4j.tracker;
import java.util.*;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Метод запрашивает пункт меню.
     * @param question приглашение типа String
     * @return Введенная строка типа String
     */
    @Override
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    /**
     * Метод возвращает пункт меню.
     * @param question приглашение типа String
     * @param range список возможных пунктов меню типа Integer
     * @return пункт меню типа int
     */
    @Override
    public int ask(String question, List<Integer> range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            throw new MenuOutException("Не в дипазоне меню");
        }
        return key;
    }
}