package org.example.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A more complex job that demonstrates using JobDataMap to pass parameters.
 */
public class DataProcessingJob implements Job {

    private static final DateTimeFormatter FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();

        String department = dataMap.getString("department");
        int batchSize = dataMap.getInt("batchSize");
        boolean verbose = dataMap.getBoolean("verbose");

        String timestamp = LocalDateTime.now().format(FORMATTER);

        System.out.println("\n[" + timestamp + "] DataProcessingJob started");
        System.out.println("  - Department: " + department);
        System.out.println("  - Batch Size: " + batchSize);
        System.out.println("  - Verbose Mode: " + verbose);

        // Simulate processing
        try {
            System.out.println("  - Processing data...");
            Thread.sleep(2000); // Simulate 2 seconds of work

            if (verbose) {
                System.out.println("  - Processed " + batchSize + " records");
                System.out.println("  - All records validated successfully");
            }

            System.out.println("  - DataProcessingJob completed successfully\n");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new JobExecutionException("Job interrupted", e);
        }
    }
}
