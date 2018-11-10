package ru.job4j.jsoup;

import org.junit.Test;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 10.11.2018
 */
public class SiteParserTest {
    @Test
    public void startSiteParser() {
        SiteParser parser = new SiteParser();
//        parser.grabSQLRU("http://www.sql.ru/forum/job-offers");

//        Date date = null;
//        try {
//            date = new SimpleDateFormat("d MMM y, H:m").parse("31 окт 18, 19:34");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        System.out.println(new SimpleDateFormat("EEE, d MMM yyyy HH:mm Z", Locale.getDefault()).format(date));
    }
}
