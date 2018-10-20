package ru.job4j.user.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 20.10.2018
 */
@ThreadSafe
public class UserStorage {
    /**
     * Список пользователей
     */
    @GuardedBy("this")
    List<User> userList = new ArrayList<>();

    /** Добавить нового пользователя
     * @param user типа User
     * @return типа boolean
     */
    synchronized public boolean add(User user) {
        return this.userList.add(user);
    }

    /** Удалить пользователя
     * @param user типа User
     * @return типа boolean
     */
    synchronized public boolean delete(User user) {
        return this.userList.remove(user);
    }

    /** Обновить пользователя
     * @param user типа User
     * @return типа boolean
     */
    synchronized public boolean update(User user) {
        boolean result = false;
        int index = this.userList.indexOf(user);
        if (index >= 0) {
            this.userList.set(index, user);
            result = true;
        }
        return result;
    }

    /** Переслать между пользователями
     * @param fromID id исходного пользователя
     * @param toID id конечного пользователя
     * @param amount размер перечислений
     * @return типа boolean
     */
    synchronized public boolean transfer(int fromID, int toID, int amount) {
        boolean result = false;
        User from = null;
        User to = null;
        for (User user: this.userList) {
            if (user.getId() == fromID) {
                from = user;
            }
            if (user.getId() == toID) {
                to = user;
            }
        }
        if ((from != null && to != null) && from.getAmount() > amount) {
            from.setAmount(from.getAmount() - amount);
            to.setAmount(to.getAmount() + amount);
            result = true;
        }
        return result;
    }
}

class User {
    /**
     * ID пользователя
     */
    private int id;
    /**
     * Объем чего-то у пользователя
     */
    private int amount;
    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }
    public int getId() {
        return id;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
