package ru.job4j.tracker;

import java.util.List;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class StubInput implements Input {
    /** Поле массив имитации ответов пользователя */
    private final String[] answers;
    /** Поле номер ответа пользователя */
    private int position;

    /**
     * Конструктор
     * @param answers типа String[]
     */
    public StubInput(final String[] answers) {
        this.answers = answers;
    }

    /**
     * Метод подставляет имитацию нажатия клавиш пользователем
     * @param question приглашение типа String
     * @return Введенная строка типа String
     */
    @Override
    public String ask(String question) {
        return this.answers[this.position++];
    }

    /**
     * Метод возвращает пункт меню.
     * @param question приглашение типа String
     * @param range список возможных пунктов меню типа Integer
     * @return пункт меню типа int
     */
    @Override
    public int ask(String question, List<Integer> range) {
        return Integer.parseInt(this.ask(question));
    }
}
