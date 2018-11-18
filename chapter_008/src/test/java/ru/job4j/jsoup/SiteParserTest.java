package ru.job4j.jsoup;

import org.junit.Test;
import java.util.Date;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 10.11.2018
 */
public class SiteParserTest {

    @Test
    public void formatDateCheck() {
        SiteParser parser = new SiteParser();
        String date = parser.formatDate("сегодня, 00:00").toString();
        String today = new Date().toString();
        String result = date.substring(0, date.indexOf(':') - 2);
        String expect = today.substring(0, today.indexOf(':') - 2);
        assertThat(result, is(expect));
    }
}
