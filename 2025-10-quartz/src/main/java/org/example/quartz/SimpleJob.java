package org.example.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A simple Quartz job that prints a message when executed.
 */
public class SimpleJob implements Job {

    private static final DateTimeFormatter FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String jobName = context.getJobDetail().getKey().getName();
        String timestamp = LocalDateTime.now().format(FORMATTER);

        System.out.println("[" + timestamp + "] SimpleJob '" + jobName + "' executed!");
        System.out.println("  - Fire time: " + context.getFireTime());
        System.out.println("  - Next fire time: " + context.getNextFireTime());
    }
}
