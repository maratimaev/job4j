package ru.job4j.diff;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 14.10.2018
 */
public class Info {
    private int newUsers;
    private int removedUsers;
    private int modifiedUsers;
    Map<Integer, String> previous = new HashMap<>();
    Map<Integer, String> current = new HashMap<>();

    public Info(List<Store.User> previous, List<Store.User> current) {
        for(Store.User user: previous){
            this.previous.put(user.getId(), user.getName());
        }

        for(Store.User user: current){
            this.current.put(user.getId(), user.getName());
        }
    }

    public int getNewUsers() {
        return newUsers;
    }

    public int getRemovedUsers() {
        return removedUsers;
    }

    public int getModifiedUsers() {
        return modifiedUsers;
    }

    public void calc() {
        for(int curr : this.current.keySet()) {
            if(!previous.containsKey(curr)) {
                newUsers++;
            }
        }
        for(int prev : this.previous.keySet()) {
            if(!current.containsKey(prev)) {
                removedUsers++;
            }
        }

    }
}
