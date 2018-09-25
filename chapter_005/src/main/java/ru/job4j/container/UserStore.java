package ru.job4j.container;

import ru.job4j.generic.SimpleArray;

import java.util.Iterator;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 25.09.2018
 */
public class UserStore implements Store{
    SimpleArray<Base> sa;

    public UserStore(int size) {
        this.sa = new SimpleArray<>(size);

    }

    @Override
    public void add(Base model) {
        this.sa.add(model);
    }

    @Override
    public boolean replace(String id, Base model) {
        boolean result = false;
        Base mdl = findById(id);
        if ( mdl != null) {
            result = this.sa.set(this.sa.indexOf(mdl), model);
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        Base model = findById(id);
        if ( model != null) {
            result = this.sa.delete(this.sa.indexOf(model));
        }
        return result;
    }

    @Override
    public Base findById(String id) {
        Base result = null;
        Iterator<Base> it = this.sa.iterator();
        while(it.hasNext()) {
            Base b = it.next();
            if (b.getId().compareTo(id) == 0) {
                result = b;
                break;
            }
        }
        return result;
    }
}
