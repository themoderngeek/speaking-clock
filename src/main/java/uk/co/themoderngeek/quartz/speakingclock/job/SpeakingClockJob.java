package uk.co.themoderngeek.quartz.speakingclock.job;

import org.joda.time.DateTime;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SpeakingClockJob implements Job {

    private static final String text = "At the third stroke, the time from BT will be %02d:%02d and %02d seconds";

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        DateTime date = new DateTime().plusSeconds(3);
        System.out.println(String.format(text, date.getHourOfDay(), date.getMinuteOfHour(), date.getSecondOfMinute()));
        beep(3);
    }

    private void beep(int count) {
        try {
            for (int i = 0; i < count; i++) {
                Thread.sleep(1000);
                System.out.println("Beep from class: " + this.toString());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

