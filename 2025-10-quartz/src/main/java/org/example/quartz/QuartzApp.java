package org.example.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.CronScheduleBuilder.cronSchedule;

/**
 * Main application class that demonstrates Quartz scheduling capabilities.
 * This app creates and schedules various types of jobs with different triggers.
 */
public class QuartzApp {

    public static void main(String[] args) {
        try {
            // Load properties and set database credentials from environment variables
            Properties properties = loadQuartzProperties();

            // Create scheduler factory and get scheduler instance
            SchedulerFactory schedulerFactory = new StdSchedulerFactory(properties);
            Scheduler scheduler = schedulerFactory.getScheduler();

            System.out.println("=".repeat(60));
            System.out.println("Quartz Scheduler Demo Application");
            System.out.println("=".repeat(60));
            System.out.println("Scheduler: " + scheduler.getSchedulerName());
            System.out.println("Instance ID: " + scheduler.getSchedulerInstanceId());
            System.out.println("=".repeat(60));
            System.out.println();

            // Schedule jobs
            scheduleSimpleJob(scheduler);
            /* 
            scheduleDataProcessingJob(scheduler);
            scheduleStatefulJob(scheduler);
            scheduleCronJob(scheduler);
            */

            // Start the scheduler
            scheduler.start();
            System.out.println("Scheduler started! Jobs are now running...");
            System.out.println("Create a file named 'stop.txt' in the project directory to stop the scheduler\n");

            // Keep the application running until stop.txt is detected
            File stopFile = Paths.get("stop.txt").toFile();
            while (!stopFile.exists()) {
                Thread.sleep(1000); // Check every second
            }

            // Shutdown
            System.out.println("\nStop file detected. Shutting down scheduler...");
            scheduler.shutdown(true);
            System.out.println("Scheduler shutdown complete.");

        } catch (SchedulerException e) {
            System.err.println("Error with scheduler: " + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("Application interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            System.err.println("Error loading configuration: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Load Quartz properties from file and override database credentials from environment variables.
     */
    private static Properties loadQuartzProperties() throws IOException {
        Properties properties = new Properties();

        // Load base properties from quartz.properties file
        try (InputStream input = QuartzApp.class.getClassLoader()
                .getResourceAsStream("quartz.properties")) {
            if (input == null) {
                throw new IOException("Unable to find quartz.properties");
            }
            properties.load(input);
        }

        // Override database credentials from environment variables
        String dbUser = System.getenv("DB_USER");
        String dbPassword = System.getenv("DB_PASSWORD");

        if (dbUser != null && !dbUser.isEmpty()) {
            properties.setProperty("org.quartz.dataSource.quartzDS.user", dbUser);
            System.out.println("Using DB_USER from environment: " + dbUser);
        } else {
            System.out.println("WARNING: DB_USER environment variable not set, using default from properties file");
        }

        if (dbPassword != null && !dbPassword.isEmpty()) {
            properties.setProperty("org.quartz.dataSource.quartzDS.password", dbPassword);
            System.out.println("Using DB_PASSWORD from environment (value hidden)");
        } else {
            System.out.println("WARNING: DB_PASSWORD environment variable not set, using default from properties file");
        }

        return properties;
    }

    /**
     * Schedule a simple job that runs every 60 seconds.
     */
    private static void scheduleSimpleJob(Scheduler scheduler) throws SchedulerException {
        JobDetail job = newJob(SimpleJob.class)
                .withIdentity("simpleJob", "group1")
                .withDescription("A simple job that prints a message")
                .build();

        Trigger trigger = newTrigger()
                .withIdentity("simpleTrigger", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(60)
                        .repeatForever())
                .build();

        scheduler.scheduleJob(job, trigger);
        System.out.println("✓ Scheduled SimpleJob (runs every 60 seconds)");
    }

    /**
     * Schedule a data processing job with parameters that runs every 15 seconds.
     */
    private static void scheduleDataProcessingJob(Scheduler scheduler) throws SchedulerException {
        JobDetail job = newJob(DataProcessingJob.class)
                .withIdentity("dataProcessingJob", "group1")
                .withDescription("Processes data with configurable parameters")
                .usingJobData("department", "Engineering")
                .usingJobData("batchSize", 100)
                .usingJobData("verbose", true)
                .build();

        Trigger trigger = newTrigger()
                .withIdentity("dataProcessingTrigger", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(15)
                        .repeatForever())
                .build();

        scheduler.scheduleJob(job, trigger);
        System.out.println("✓ Scheduled DataProcessingJob (runs every 15 seconds)");
    }

    /**
     * Schedule a stateful job that maintains execution count.
     */
    private static void scheduleStatefulJob(Scheduler scheduler) throws SchedulerException {
        JobDetail job = newJob(StatefulJob.class)
                .withIdentity("statefulJob", "group2")
                .withDescription("A job that maintains state between executions")
                .usingJobData("executionCount", 0)
                .build();

        Trigger trigger = newTrigger()
                .withIdentity("statefulTrigger", "group2")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(8)
                        .repeatForever())
                .build();

        scheduler.scheduleJob(job, trigger);
        System.out.println("✓ Scheduled StatefulJob (runs every 8 seconds)");
    }

    /**
     * Schedule a cron-based job (runs every minute at 0 seconds).
     */
    private static void scheduleCronJob(Scheduler scheduler) throws SchedulerException {
        JobDetail job = newJob(SimpleJob.class)
                .withIdentity("cronJob", "group2")
                .withDescription("A cron-scheduled job")
                .build();

        // Run at the top of every minute
        Trigger trigger = newTrigger()
                .withIdentity("cronTrigger", "group2")
                .withSchedule(cronSchedule("0 * * * * ?"))
                .build();

        scheduler.scheduleJob(job, trigger);
        System.out.println("✓ Scheduled CronJob (runs every minute at 0 seconds)");
    }
}
