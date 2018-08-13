package ru.job4j.tracker;
import  java.util.*;

public class MenuTracker {
//    private static final int ADD = 0;
//    private static final String SHOWALL = "1";
//    private static final String EDIT = "2";
//    private static final String DELETE = "3";
//    private static final String FINDBYID = "4";
//    private static final String FINDBYNAME = "5";
//    private static final String EXIT = "6";
    private Input input;
    private Tracker tracker;
    private List<UserAction> actions = new ArrayList<>();

    /**
     * Конструктор.
     * @param input   объект типа Input
     * @param tracker объект типа Tracker
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Метод для получения массива меню.
     * @return длину массива
     */
    public int getActionsLentgh() {
        return this.actions.size();
    }

    /**
     * Метод заполняет массив.
     */
    public void fillActions() {
        this.actions.add(new AddItem(0, ". Добавление новой заявки"));
        this.actions.add(new ShowItems(1, ". Список всех заявок"));
        this.actions.add(new EditItem(2, ". Изменение заявки"));
        this.actions.add(new DeleteItem(3, ". Удаление заявки"));
        this.actions.add(new FindItemById(4, ". Поиск заявки по ID"));
        this.actions.add(new FindItemsByName(5, ". Поиск заявки по имени"));
//        this.actions.add(new ExitProgram(6, ". Выход"));
    }

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

    public class AddItem extends MenuAction {
        public AddItem(int menuKey, String menuString) {
         super(menuKey, menuString);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Добавление новой заявки --------------");
            String name = input.ask("Введите имя заявки : ");
            String desc = input.ask("Введите описание заявки : ");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("Новая заявка с getId : " + item.getId());
        }
    }
    public class ShowItems extends MenuAction {
        public ShowItems(int menuKey, String menuString) {
            super(menuKey, menuString);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Список всех заявок -------------");
            for (Item item: tracker.getAll()) {
                System.out.println(item.getName() + " " + item.getDescription() + " " + item.getId());
            }
            System.out.println("---------------------------------------------");
        }
    }
    public class EditItem extends MenuAction {
        public EditItem(int menuKey, String menuString) {
            super(menuKey, menuString);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Изменение поступившей заявки --------------");
            String id = input.ask("Введите id заявки : ");
            String name = input.ask("Введите новое имя заявки : ");
            String desc = input.ask("Введите новое описание заявки : ");
            Item item = new Item(name, desc);
            tracker.replace(id, item);
        }
    }
    public class DeleteItem extends MenuAction {
        public DeleteItem(int menuKey, String menuString) {
            super(menuKey, menuString);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Удаление заявки --------------");
            String id = input.ask("Введите id заявки : ");
            Item item = tracker.findById(id);
            String sure = input.ask("Удалить заявку " + item.getName() + " " + item.getDescription() + " ? (yes/no) ");
            if (sure.equals("yes")) {
                tracker.delete(id);
            }
        }

    }
    public class FindItemById extends MenuAction {
        public FindItemById(int menuKey, String menuString) {
            super(menuKey, menuString);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите id заявки : ");
            Item item = tracker.findById(id);
            System.out.println("Имя заявки: " + item.getName() + " Описание заявки: " + item.getDescription());
            System.out.println("-------------------------------------------");
        }

    }
    public class FindItemsByName extends MenuAction {
        public FindItemsByName(int menuKey, String menuString) {
            super(menuKey, menuString);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Введите имя заявки : ");
            Item[] items = tracker.findByName(name);
            for (Item item: items) {
                if (item != null) {
                    System.out.println(" Имя заявки: " + item.getName()
                            + " Описание заявки: " + item.getDescription()
                            + " ID заявки: " + item.getId());
                }
            }
            System.out.println("-------------------------------------------");
        }

    }

    public class MenuAction implements UserAction {
        private int menuKey;
        private String menuString;

        public MenuAction(int menuKey, String menuString) {
            this.menuKey = menuKey;
            this.menuString = menuString;
        }

//        @Override
//        public int key() {
//            return this.menuKey;
//        }

        @Override
        public void execute(Input input, Tracker tracker) { }

        @Override
        public String info() {
            return this.menuKey + this.menuString;
        }
    }
}
