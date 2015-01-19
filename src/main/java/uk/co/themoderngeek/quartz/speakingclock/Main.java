package uk.co.themoderngeek.quartz.speakingclock;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import uk.co.themoderngeek.quartz.speakingclock.job.SpeakingClockJob;

public class Main {

    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();

        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail job = JobBuilder.newJob(SpeakingClockJob.class).withIdentity("speaking-clock", "group-1").build();

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("5-second-trigger", "group-1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?"))
                .build();

        scheduler.start();

        scheduler.scheduleJob(job, trigger);
    }
}
