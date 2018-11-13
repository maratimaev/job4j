package ru.job4j.jsoup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 13.11.2018
 */
public class StartJob implements Job {
    private static final Logger LOGGER = LoggerFactory.getLogger(PostrgreDB.class.getName());

    Calendar c;
    Date d;
    SimpleDateFormat sdf;

    public StartJob() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        c = new GregorianCalendar();
        d = c.getTime();
        sdf = new SimpleDateFormat("d MMMMM yyyy - HH:mm:ss aaa");
        String msg = String.format("Job Name - %s, Current Time - %s", context.getJobDetail().getKey(), sdf.format(d));
        System.out.println(msg);
    }
}

 class SimpleQuartzDemo {
    public static void main(String[] args) {
        SimpleQuartzDemo obj = new SimpleQuartzDemo();
        obj.runDemo();
    }

    public void runDemo() {
        try {
            // First we must get a reference to a scheduler
            SchedulerFactory sf = new StdSchedulerFactory();
            Scheduler sched = sf.getScheduler();

            /**
             * Job 1 using Trigger
             */
            JobDetail job1 = JobBuilder.newJob(StartJob.class)
                    .withIdentity("currentTime-Job-1", "group1")
                    .build();

            //This trigger will run every minute in infinite loop
            Trigger trigger1 = TriggerBuilder.newTrigger()
                    .withIdentity("everyMinuteTrigger", "group1")
                    .startAt(new Date(System.currentTimeMillis()))
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *"))
                    .build();

            Date ft = sched.scheduleJob(job1, trigger1);
            sched.start();

            System.out.println(job1.getKey() + " has been scheduled to run at: " + ft);

            /**
             * Job 2 using SimpleTrigger
             */
            JobDetail job2 = JobBuilder.newJob(StartJob.class)
                    .withIdentity("currentTime-Job-2", "group1")
                    .build();

            // get a "nice round" time a few seconds in the future....
            Date startTime = DateBuilder.nextGivenSecondDate(null, 10);

            //This trigger will run every 10 sec for 4 times
            SimpleTrigger trigger2 = TriggerBuilder.newTrigger()
                    .withIdentity("fourTimesTrigger", "group1")
                    .startAt(startTime)
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(10)
                            .withRepeatCount(4))
                    .build();

            ft = sched.scheduleJob(job2, trigger2);
            sched.start();

            System.out.println(job1.getKey() + " has been scheduled to run at: " + ft);

            /**
             * Job 3 Using CronTrigger
             */
            JobDetail job3 = JobBuilder.newJob(StartJob.class)
                    .withIdentity("currentTime-Job-3", "newGroup")
                    .build();

            //run every 20 seconds
            CronTrigger trigger3 = TriggerBuilder.newTrigger()
                    .withIdentity("twentySec", "group2")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * * * ?"))
                    .build();

            ft = sched.scheduleJob(job3, trigger3);
            sched.start();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
