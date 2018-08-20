package ru.job4j.tracker;

import java.util.List;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class ValidateInput implements Input {
    private final Input input;

    /**
     * Конструктор
     * @param input типа Input
     */
    public ValidateInput(final Input input) {
        this.input = input;
    }

    /**
     * Метод запрашивает пункт меню.
     * @param question приглашение типа String
     * @return Введенная строка типа String
     */
    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    /**
     * Метод обрабатывает ошибки при выборе пукта меню
     * @param question приглашение типа String
     * @param range список возможных пунктов меню типа Integer
     * @return пункт меню типа int
     */
    @Override
    public int ask(String question, List<Integer> range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Пожалуйста введите цифру");
            } catch (MenuOutException moe) {
                System.out.println("Пожалуйста введите данные в диапазоне меню");
            }
        } while (invalid);
        return value;
    }
}
