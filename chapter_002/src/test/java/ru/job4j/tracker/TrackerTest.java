package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    /** Проверка добавления заявки */
	@Test
	public void whenAddNewItemThenTrackerHasSameItem() {
 		Tracker tracker = new Tracker();
 		Item item = new Item("test1", "testDescription");
		//Item item1 = new Item("test2", "testDescription2", 1232L);
 		tracker.add(item);
 		assertThat(tracker.getAll().get(0), is(item));
	}
    /** Проверка поиска заявки по id */
	@Test
	public void whenFindById() {
		Tracker tracker = new Tracker();
    	Item item = new Item("test1", "testDescription");
		tracker.add(item);
    	assertThat(tracker.findById(item.getId()), is(item));
	}
    /** Проверка поиска заявок по имени */
	@Test
	public void whenFindByName() {
		Tracker tracker = new Tracker();
    	Item item = new Item("test1", "testDescription");
		tracker.add(item);
        Item item1 = new Item("test2", "testDescription2");
        tracker.add(item1);
        Item item2 = new Item("test1", "testDescription1");
        tracker.add(item2);
        Item result = (tracker.findByName("test1")).get(1);
    	assertThat(result, is(item2));
	}
    /** Проверка редактирования заявки */
	@Test
	public void whenReplaceNameThenReturnNewName() {
    	Tracker tracker = new Tracker();
    	Item previous = new Item("test1", "testDescription");
    	tracker.add(previous);
    	Item next = new Item("test2", "testDescription2");
    	next.setId(previous.getId());
    	tracker.replace(previous.getId(), next);
    	assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
	}
    /** Проверка удаления заявки */
	@Test
	public void whenDelete() {
    	Tracker tracker = new Tracker();
    	Item first = new Item("test1", "testDescription");
    	tracker.add(first);
    	Item second = new Item("test2", "testDescription2");
    	tracker.add(second);
		Item third = new Item("test3", "testDescription3");
    	tracker.add(third);
		tracker.delete(second.getId());
    	assertThat((tracker.getAll().get(1)).getName(), is("test3"));
	}

}