package ru.job4j.xml;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 05.11.2018
 */
public class StoreSQLTest {
    @Test
    public void checkConnection() {
        StoreSQL sql = new StoreSQL();
        sql.generate(30000);
    }
}
