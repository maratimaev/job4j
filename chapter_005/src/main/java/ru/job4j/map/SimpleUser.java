package ru.job4j.map;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 11.10.2018
 */
public class SimpleUser {

    private String name;

    public SimpleUser(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SimpleUser that = (SimpleUser) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
