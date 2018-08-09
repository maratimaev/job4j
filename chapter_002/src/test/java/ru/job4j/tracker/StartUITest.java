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
    String menu = "Меню.\r\n"
                    + "0. Add new Item\r\n"
                    + "1. Show all items\r\n"
                    + "2. Edit item\r\n"
                    + "3. Delete item\r\n"
                    + "4. Find item by Id\r\n"
                    + "5. Find items by name\r\n"
                    + "6. Exit Program\r\n";
    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }
    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.getAll()[0].getName(), is("test name"));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "заменили заявку", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }
    @Test
    public void whenDeleteThenTrackerHasDeleteValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Item item1 = tracker.add(new Item("test name1", "desc1"));
        Input input = new StubInput(new String[]{"3", item.getId(), "yes", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.getAll()[0].getName(), is("test name1"));
    }
    @Test
    public void whenShowAllThenPrintItemstoScreen() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Item item1 = tracker.add(new Item("test name1", "desc1"));
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                        .append(this.menu)
                        .append("------------ Список всех заявок -------------\r\n")
                        .append("test name desc " + item.getId())
                        .append(System.lineSeparator())
                        .append("test name1 desc1 " + item1.getId())
                        .append(System.lineSeparator())
                        .append("---------------------------------------------\r\n")
                        .append(this.menu)
                        .toString()
                )
        );
    }
    @Test
    public void whenFindByIdThenPrintItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Item item1 = tracker.add(new Item("test name1", "desc1"));
        Input input = new StubInput(new String[]{"4", item.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                        .append(this.menu)
                        .append("Имя заявки: test name Описание заявки: desc\r\n")
                        .append("-------------------------------------------\r\n")
                        .append(this.menu)
                        .toString()
                )
        );
    }
    @Test
    public void whenFindByNameThenPrintItems() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Item item1 = tracker.add(new Item("test name1", "desc1"));
        Item item2 = tracker.add(new Item("test name", "desc2"));
        Input input = new StubInput(new String[]{"5", item.getName(), "6"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                        .append(this.menu)
                        .append(" Имя заявки: test name Описание заявки: desc ID заявки: " + item.getId())
                        .append(System.lineSeparator())
                        .append(" Имя заявки: test name Описание заявки: desc2 ID заявки: " + item2.getId())
                        .append(System.lineSeparator())
                        .append("-------------------------------------------\r\n")
                        .append(this.menu)
                        .toString()
                )
        );
    }
}
