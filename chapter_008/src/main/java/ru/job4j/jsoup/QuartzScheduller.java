package ru.job4j.jsoup;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 14.11.2018
 */
public class QuartzScheduller {
    private static final Logger LOGGER = LoggerFactory.getLogger(PostrgreDB.class.getName());
    public static void main(String[] args) {
        QuartzScheduller quartzScheduller = new QuartzScheduller();
        quartzScheduller.runParser();
    }

    public void runParser() {
        try {
            InputStream file = QuartzScheduller.class.getClassLoader().getResourceAsStream("app.properties");
            Properties properties = new Properties();
            properties.load(file);
            file.close();

            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobDetail job = JobBuilder.newJob(StartJob.class)
                    .withIdentity("Parser", "ParserGroup").build();

            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("everyMinute", "TriggerGroup")
                    .withSchedule(CronScheduleBuilder.cronSchedule(properties.getProperty("crontime"))).build();

            Date date = scheduler.scheduleJob(job, trigger);
            LOGGER.info(String.format("%s has been scheduled to run at: %s", job.getKey(), date));
            scheduler.start();

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
