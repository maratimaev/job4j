package ru.job4j.list;

import java.util.HashMap;
import java.util.List;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class UserConvert {

    /**
     * Метод конвертирует список объектов User в HashMap
     * @param list типа List<User>
     * @return HashMap<Integer, User>, где Integer это id пользователя
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<>();

        for (User current : list) {
            result.put(current.getId(), current);
        }
        return result;
    }
}
