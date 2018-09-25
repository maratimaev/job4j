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
        this.sa.set(0,model);
        return false;
    }

    @Override
    public boolean delete(String id) {

        this.sa.delete()
        return false;
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
