package ru.job4j.sql;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 01.11.2018
 */
public class TrackerSQLTest {

    @Test
    public void whenConnectToDB() {
        TrackerSQL trackerSQL = new TrackerSQL();
        trackerSQL.init();
    }
}
