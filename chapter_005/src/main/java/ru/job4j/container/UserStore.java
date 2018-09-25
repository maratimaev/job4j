package ru.job4j.container;

import ru.job4j.generic.SimpleArray;


/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 25.09.2018
 */
public class UserStore extends AbstractStore<User> {
    SimpleArray<User> sa;

    public UserStore(int size) {
        this.sa = new SimpleArray<>(size);

    }

//    @Override
//    public void add(User model) {
//        this.sa.add(model);
//    }

    @Override
    public boolean replace(String id, User model) {
        boolean result = false;
        User mdl = findById(id);
        if ( mdl != null) {
            result = this.sa.set(this.sa.indexOf(mdl), model);
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        User model = findById(id);
        if ( model != null) {
            result = this.sa.delete(this.sa.indexOf(model));
        }
        return result;
    }

    @Override
    public User findById(String id) {
        User result = null;
        for (User b : (Iterable<User>) this.sa) {
            if (b.getId().compareTo(id) == 0) {
                result = b;
                break;
            }
        }
        return result;
    }
}
