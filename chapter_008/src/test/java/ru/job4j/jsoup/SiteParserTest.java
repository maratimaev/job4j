package ru.job4j.jsoup;

import org.junit.Test;
import java.util.ArrayList;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 10.11.2018
 */
public class SiteParserTest {
    @Test
    public void startSiteParser() {
        SiteParser parser = new SiteParser();
        PostrgreDB db = new PostrgreDB();
        db.connect();
        SQLRU sql = db.getLast();
        ArrayList<SQLRU> list = parser.grabSQLRU("http://www.sql.ru/forum/job-offers", sql);
        db.add(list);
    }
}
