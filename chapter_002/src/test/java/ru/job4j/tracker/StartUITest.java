package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {
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
    public void whenFindByNameThenTrackerReturnArrayOfFindItems() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Item item1 = tracker.add(new Item("test name1", "desc1"));
        Item item2 = tracker.add(new Item("test name", "desc2"));
        Input input = new StubInput(new String[]{"5", item.getName(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.getAll()[1].getDescription(), is("desc2"));
    }
}
