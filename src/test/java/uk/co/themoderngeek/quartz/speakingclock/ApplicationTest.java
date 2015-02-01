package uk.co.themoderngeek.quartz.speakingclock;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import uk.co.themoderngeek.quartz.speakingclock.job.SpeakingClockJob;

@ContextConfiguration(locations = {"classpath:config.xml"})
public class ApplicationTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private SchedulerFactoryBean quartzScheduler;

    @Test
  //  @Ignore
    public void test() throws SchedulerException {
        quartzScheduler.getScheduler().clear();
    }

    @Test
   // @Ignore
    public void addJob() throws SchedulerException {

        JobDetail job = JobBuilder.newJob(SpeakingClockJob.class).withIdentity("speaking-clock", "group-1").build();

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("5-second-trigger", "group-1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();
        quartzScheduler.getScheduler().scheduleJob(job, trigger);
    }
}
