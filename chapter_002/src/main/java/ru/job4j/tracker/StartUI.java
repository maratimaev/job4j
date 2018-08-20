package ru.job4j.tracker;

import java.util.*;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class StartUI {
    private final Input input;
    private final Tracker tracker;
    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основной метод работы с меню
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        menu.setRange();
        do {
            try {
                menu.show();
                menu.select(input.ask("select:", menu.range));
            } catch (ItemNotFoundException infe) {
                System.out.println("Заявка с таким id не найдена");
            }
        } while (!"y".equals(this.input.ask("Exit?(y): ")));
    }
    /**
     * Запуск программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker()).init();
    }
}
