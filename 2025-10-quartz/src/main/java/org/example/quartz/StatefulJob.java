package org.example.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A stateful job that maintains state between executions.
 * The @PersistJobDataAfterExecution annotation ensures that changes to the
 * JobDataMap are persisted after each execution.
 */
@PersistJobDataAfterExecution
public class StatefulJob implements Job {

    private static final DateTimeFormatter FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();

        int executionCount = dataMap.getInt("executionCount");
        executionCount++;

        String timestamp = LocalDateTime.now().format(FORMATTER);

        System.out.println("[" + timestamp + "] StatefulJob execution #" + executionCount);
        System.out.println("  - This job maintains state between executions");
        System.out.println("  - Total executions so far: " + executionCount);

        // Update the execution count in the data map
        dataMap.put("executionCount", executionCount);

        if (executionCount % 5 == 0) {
            System.out.println("  - Milestone: Completed " + executionCount + " executions!");
        }
    }
}
