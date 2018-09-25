package ru.job4j.container;

/**
 *
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 25.09.2018
 */
public abstract class Base {
    private final String id;

    protected Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}