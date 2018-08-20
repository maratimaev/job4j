package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {
    PrintStream stdout = System.out;
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    /** Поле строка с выводом всех пунктов */
    String menu = new StringBuilder().append("0. Добавление новой заявки")
                                    .append(System.lineSeparator())
                                    .append("1. Список всех заявок")
                                    .append(System.lineSeparator())
                                    .append("2. Изменение заявки")
                                    .append(System.lineSeparator())
                                    .append("3. Удаление заявки")
                                    .append(System.lineSeparator())
                                    .append("4. Поиск заявки по ID")
                                    .append(System.lineSeparator())
                                    .append("5. Поиск заявки по имени")
                                    .append(System.lineSeparator())
                                    .append("6. Выход")
                                    .append(System.lineSeparator()).toString();
    /**
     *  Метод заменяет стандартный вывод
     */
    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    /**
     *  Метод возвращает стандартный вывод
     */
    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    /**
     *  Метод имитирует добавление заявки
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.getAll()[0].getName(), is("test name"));
    }

    /**
     *  Метод имитирует редактирование заявки
     */
    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "заменили заявку", "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }

    /**
     *  Метод имитирует удаление заявки
     */
    @Test
    public void whenDeleteThenTrackerHasDeleteValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Item item1 = tracker.add(new Item("test name1", "desc1"));
        Input input = new StubInput(new String[]{"3", item.getId(), "yes", "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.getAll()[0].getName(), is("test name1"));
    }

    /**
     *  Метод имитирует вывод заявок на экран
     */
    @Test
    public void whenShowAllThenPrintItemsToScreen() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Item item1 = tracker.add(new Item("test name1", "desc1"));
        Input input = new StubInput(new String[]{"1", "y"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                        .append(this.menu)
                        .append("------------ Список всех заявок -------------")
                        .append(System.lineSeparator())
                        .append("test name desc ").append(item.getId())
                        .append(System.lineSeparator())
                        .append("test name1 desc1 ").append(item1.getId())
                        .append(System.lineSeparator())
                        .append("---------------------------------------------")
                        .append(System.lineSeparator())
                        .toString()
                )
        );
    }

    /**
     *  Метод имитирует поиск заявки по id
     */
    @Test
    public void whenFindByIdThenPrintItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Item item1 = tracker.add(new Item("test name1", "desc1"));
        Input input = new StubInput(new String[]{"4", item.getId(), "y"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                        .append(this.menu)
                        .append("Имя заявки: test name Описание заявки: desc")
                        .append(System.lineSeparator())
                        .append("-------------------------------------------")
                        .append(System.lineSeparator())
                        .toString()
                )
        );
    }

    /**
     *  Метод имитирует ввод неверного id
     */
    @Test
    public void whenFindByIdThenNoItem() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"4", "12345", "y"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                        .append(this.menu)
                        .append("Заявка с таким id не найдена")
                        .append(System.lineSeparator())
                        .toString()
                )
        );
    }

    /**
     *  Метод имитирует ввод неверного пункта меню
     */
    @Test
    public void whenWrongMenuNumberThenException() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"9", "6", "y"});
        new StartUI(new ValidateInput(input), tracker).init();
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                        .append(this.menu)
                        .append("Пожалуйста введите данные в диапазоне меню")
                        .append(System.lineSeparator())
                        .toString()
                )
        );
    }

    /**
     *  Метод имитирует поиск заявки по имени
     */
    @Test
    public void whenFindByNameThenPrintItems() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Item item1 = tracker.add(new Item("test name1", "desc1"));
        Item item2 = tracker.add(new Item("test name", "desc2"));
        Input input = new StubInput(new String[]{"5", item.getName(), "y"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                        .append(this.menu)
                        .append(" Имя заявки: test name Описание заявки: desc ID заявки: ").append(item.getId())
                        .append(System.lineSeparator())
                        .append(" Имя заявки: test name Описание заявки: desc2 ID заявки: ").append(item2.getId())
                        .append(System.lineSeparator())
                        .append("-------------------------------------------")
                        .append(System.lineSeparator())
                        .toString()
                )
        );
    }

}