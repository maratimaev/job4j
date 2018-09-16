package ru.job4j.tracker;
import  java.util.*;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private StartUI ui;
    /** Поле список возможных действий пользователя */
    private List<UserAction> actions = new ArrayList<>();
    /** Поле список номеров пунктов */
    List<Integer> range = new ArrayList<>();

    /**
     * Конструктор.
     * @param input   объект типа Input
     * @param tracker объект типа Tracker
     */
    public MenuTracker(Input input, Tracker tracker, StartUI ui) {
        this.input = input;
        this.tracker = tracker;
        this.ui = ui;
    }

    /**
     * Метод заполняет список номерами пунктов.
     */
    public void setRange() {
        for (UserAction action: actions) {
            range.add(action.key());
        }
    }

    /**
     * Метод заполняет массив.
     */
    public void fillActions() {
        this.actions.add(this.new AddItem(0, "Добавление новой заявки"));
        this.actions.add(this.new ShowItems(1, "Список всех заявок"));
        this.actions.add(this.new EditItem(2, "Изменение заявки"));
        this.actions.add(this.new DeleteItem(3, "Удаление заявки"));
        this.actions.add(this.new FindItemById(4, "Поиск заявки по ID"));
        this.actions.add(this.new FindItemsByName(5, "Поиск заявки по имени"));
        this.actions.add(this.new ExitProgram(6, "Выход"));
    }

//    public void fillActionsL(){
//
//    }

    /**
     * Метод в зависимости от указанного ключа, выполняет соотвествующие действие.
     * @param key ключ операции
      */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }
    /**
     * Метод выводит на экран меню.
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Внутренний класс добавление заявки в tracker
     */
    public class AddItem extends BaseAction {
        /**
         * Конструктор
         * @param menuKey типа int
         * @param menuString типа String
         */
        public AddItem(int menuKey, String menuString) {
            super(menuKey, menuString);
        }

        /**
         * Метод создает новую заявку
         * @param input типа Input
         * @param tracker типа Tracker
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Добавление новой заявки --------------");
            String name = input.ask("Введите имя заявки : ");
            String desc = input.ask("Введите описание заявки : ");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.printf("Новая заявка с getId : %s%n", item.getId());
        }
    }

    /**
     * Внутренний класс выводит список заявок
     */
    public class ShowItems extends BaseAction {
        /**
         * Конструктор
         * @param menuKey типа int
         * @param menuString типа String
         */
        public ShowItems(int menuKey, String menuString) {
            super(menuKey, menuString);
        }

        /**
         * Метод выводит список заявок
         * @param input типа Input
         * @param tracker типа Tracker
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Список всех заявок -------------");
            for (Item item: tracker.getAll()) {
                System.out.printf("%s %s %s%n", item.getName(), item.getDescription(), item.getId());
            }
            System.out.println("---------------------------------------------");
        }
    }

    /**
     * Внутренний класс редактирование заявки
     */
    public class EditItem extends BaseAction {
        /**
         * Конструктор
         * @param menuKey типа int
         * @param menuString типа String
         */
        public EditItem(int menuKey, String menuString) {
            super(menuKey, menuString);
        }

        /**
         * Метод редактирует заявку
         * @param input типа Input
         * @param tracker типа Tracker
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Изменение поступившей заявки --------------");
            String id = input.ask("Введите id заявки : ");
            if (tracker.findById(id) == null) {
                System.out.println("Заявка с таким id не найдена");
            } else {
                String name = input.ask("Введите новое имя заявки : ");
                String desc = input.ask("Введите новое описание заявки : ");
                Item item = new Item(name, desc);
                tracker.replace(id, item);
            }
        }
    }

    /**
     * Внутренний класс удаление заявки
     */
    public class DeleteItem extends BaseAction {
        /**
         * Конструктор
         * @param menuKey типа int
         * @param menuString типа String
         */
        public DeleteItem(int menuKey, String menuString) {
            super(menuKey, menuString);
        }

        /**
         * Метод удаляет заявку
         * @param input типа Input
         * @param tracker типа Tracker
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Удаление заявки --------------");
            String id = input.ask("Введите id заявки : ");
            Item item = tracker.findById(id);
            if (item == null) {
                System.out.println("Заявка с таким id не найдена");
            } else {
                String sure = input.ask("Удалить заявку " + item.getName() + " " + item.getDescription() + " ? (yes/no) ");
                if (sure.equals("yes")) {
                    tracker.delete(id);
                }
            }
        }

    }

    /**
     * Внутренний класс поиск заявки по id
     */
    public class FindItemById extends BaseAction {
        /**
         * Конструктор
         * @param menuKey типа int
         * @param menuString типа String
         */
        public FindItemById(int menuKey, String menuString) {
            super(menuKey, menuString);
        }

        /**
         * Метод ищет заявку по id
         * @param input типа Input
         * @param tracker типа Tracker
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите id заявки : ");
            Item item = tracker.findById(id);
            if (item == null) {
                System.out.println("Заявка с таким id не найдена");
            } else {
                System.out.printf("Имя заявки: %s Описание заявки: %s%n",
                        item.getName(), item.getDescription());
                System.out.println("-------------------------------------------");
            }
        }
    }

    /**
     * Внутренний класс поиск заявки по имени
     */
    public class FindItemsByName extends BaseAction {
        /**
         * Конструктор
         * @param menuKey типа int
         * @param menuString типа String
         */
        public FindItemsByName(int menuKey, String menuString) {
            super(menuKey, menuString);
        }

        /**
         * Метод ищет заявки по имени
         * @param input типа Input
         * @param tracker типа Tracker
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Введите имя заявки : ");
            ArrayList<Item> items = tracker.findByName(name);
            for (Item item: items) {
                if (item != null) {
                    System.out.printf(" Имя заявки: %s Описание заявки: %s ID заявки: %s%n",
                            item.getName(), item.getDescription(), item.getId());
                }
            }
            System.out.println("-------------------------------------------");
        }

    }

    /**
     * Внутренний класс выход из программы
     */
    public class ExitProgram extends BaseAction {
        /**
         * Конструктор
         * @param menuKey типа int
         * @param menuString типа String
         */
        public ExitProgram(int menuKey, String menuString) {
            super(menuKey, menuString);
        }

         /**
         * Метод выход из программы
         * @param input типа Input
         * @param tracker типа Tracker
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("Выбран выход из программы. До свидания! -=^_^=-");
            MenuTracker.this.ui.setWorking(false);
        }
    }
}
