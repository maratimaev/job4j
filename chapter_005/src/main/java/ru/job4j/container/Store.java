package ru.job4j.container;

/**
 *
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 25.09.2018
 */
public interface Store<T extends Base> {

    void add(T model);

    boolean replace(String id, T model);

    boolean delete(String id);

    T findById(String id);
}
