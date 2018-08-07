package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {
	@Test
	public void whenAddNewItemThenTrackerHasSameItem() {
 		Tracker tracker = new Tracker();
 		Item item = new Item("test1", "testDescription", 123L);
		//Item item1 = new Item("test2", "testDescription2", 1232L);
 		tracker.add(item);
 		assertThat(tracker.getAll()[0], is(item));
	}
	@Test
	public void whenFindById() {
		Tracker tracker = new Tracker();
    	Item item = new Item("test1", "testDescription", 123L);
		tracker.add(item);
    	assertThat(tracker.findById(item.getId()), is(item));
	}
	@Test
	public void whenFindByName() {
		Tracker tracker = new Tracker();
    	Item item = new Item("test1", "testDescription", 123L);
		tracker.add(item);
        Item item1 = new Item("test2", "testDescription2", 123L);
        tracker.add(item1);
        Item item2 = new Item("test1", "testDescription1", 123L);
        tracker.add(item2);
        Item result = (tracker.findByName("test1"))[1];
    	assertThat(result, is(item2));
	}

	@Test
	public void whenReplaceNameThenReturnNewName() {
    	Tracker tracker = new Tracker();
    	Item previous = new Item("test1", "testDescription", 123L);
    	tracker.add(previous);
    	Item next = new Item("test2", "testDescription2", 1234L);
    	next.setId(previous.getId());
    	tracker.replace(previous.getId(), next);
    	assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
	}
	@Test
	public void whenDelete() {
    	Tracker tracker = new Tracker();
    	Item first = new Item("test1", "testDescription", 123L);
    	tracker.add(first);
    	Item second = new Item("test2", "testDescription2", 1234L);
    	tracker.add(second);
		Item third = new Item("test3", "testDescription3", 12345L);
    	tracker.add(third);
		tracker.delete(second.getId());
    	assertThat((tracker.getAll()[1]).getName(), is("test3"));
	}

}