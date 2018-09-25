package ru.job4j.container;

import ru.job4j.generic.SimpleArray;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 25.09.2018
 */
public abstract class AbstractStore<E extends Base> implements Store<E> {

    SimpleArray<E> saas;

    @Override
    public void add(E model) {
        this.saas.add(model);
    }

    @Override
    public boolean replace(String id, E model) {
        boolean result = false;
        E mdl = findById(id);
        if ( mdl != null) {
            result = this.saas.set(this.saas.indexOf(mdl), model);
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public E findById(String id) {
        return null;
    }
}
