package ru.job4j.user.storage;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 20.10.2018
 */
public class UserStorageTest {

    @Test
    public void whenTransfer50FromUser1ThenUser1Have50() {
        User user1 = new User(1, 100);
        User user2 = new User(2, 200);
        UserStorage userStorage = new UserStorage();
        assertThat(userStorage.add(user1), is(true));
        assertThat(userStorage.add(user2), is(true));
        assertThat(userStorage.transfer(1, 2, 50), is(true));
        assertThat(user1.getAmount(), is(50));
    }
}
