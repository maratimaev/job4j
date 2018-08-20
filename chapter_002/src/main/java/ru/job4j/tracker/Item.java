package ru.job4j.tracker;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class Item {
	private String id;
	public String name;
	public String description;

    /**
     * Конструктор.
     */
	public Item() {
	}

    /**
     * Конструктор.
     * @param name имя заявки
     * @param description описание заявки
     */
	public Item(String name, String description) {
		this.name = name;
		this.description = description;
	}

    /**
     * Геттер возвращает имя заявки.
     * @return имя заявки типа String
     */
	public String getName() {
		return this.name;
	}

    /**
     * Геттер возвращает описание заявки.
     * @return описание заявки типа String
     */
	public String getDescription() {
		return this.description;
	}

    /**
     * Геттер возвращает id заявки.
     * @return id заявки типа String
     */
	public String getId() {
		return this.id;
	}

    /**
     * Сеттер присваивает id заявки.
     * @param id заявки
     */
	public void setId(String id) {
		this.id = id;
	}
}