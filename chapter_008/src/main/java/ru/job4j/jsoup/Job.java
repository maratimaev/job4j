package ru.job4j.jsoup;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 13.11.2018
 */
public class Job implements org.quartz.Job {
    private static final Logger LOGGER = LoggerFactory.getLogger(PostrgreDB.class.getName());

    @Override
    public void execute(JobExecutionContext context) {
        LOGGER.info(String.format("Job Name - %s, Current Time - %s", context.getJobDetail().getKey(), new GregorianCalendar().getTime()));
        SiteParser parser = new SiteParser();
        PostrgreDB db = new PostrgreDB();
        db.connect();
        SQLRU sql = db.getLast();
        ArrayList<SQLRU> list = parser.grabSQLRU("http://www.sql.ru/forum/job-offers", sql);
        db.add(list);
    }
}
