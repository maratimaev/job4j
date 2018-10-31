package ru.job4j.tracker;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class StartUI {
    private final Input input;
    private final ITracker tracker;
    /** Поле признак выхода из прграммы*/
    private boolean working = true;
    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, ITracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    /**
     * Основной метод работы с меню
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker, this);
        menu.fillActions();
        do {
            menu.show();
            menu.select(input.ask("select:", menu.getRange()));
        } while (this.working);
    }
    /**
     * Запуск программы.
     * @param args типа String[]
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), Tracker.getInstance()).init();
    }
}
