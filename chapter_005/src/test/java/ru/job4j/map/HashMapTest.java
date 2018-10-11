package ru.job4j.map;

import org.junit.Test;

import javax.swing.plaf.basic.BasicScrollPaneUI;
import java.util.HashMap;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 11.10.2018
 */
public class HashMapTest {
    private HashMap<SimpleUser, String> hashMap = new HashMap<>();



    @Test
    public void whenAdd30ElementsToMap() {
        for (int i = 0; i < 30; i++) {
            SimpleUser user  = new SimpleUser("i" + i);
            hashMap.put(user, "test" + i);
        }
    }
}
