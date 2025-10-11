# Quartz Scheduler Demo Application

A command-line Java application demonstrating basic Quartz Scheduler features with PostgreSQL persistence.

## Features

This demo includes several example jobs:

1. **SimpleJob** - A basic job that prints a message every 10 seconds
2. **DataProcessingJob** - Demonstrates passing parameters via JobDataMap (runs every 15 seconds)
3. **StatefulJob** - Shows how to maintain state between executions with `@PersistJobDataAfterExecution` (runs every 8 seconds)
4. **CronJob** - Demonstrates cron-based scheduling (runs every minute)

## Technologies

- Java 21
- Quartz Scheduler 2.3.2
- PostgreSQL (JDBC JobStore)
- Gradle 8.x

## Prerequisites

- Java 21 or higher
- PostgreSQL 12 or higher
- Gradle 8.x (or use the Gradle wrapper)

## Setup

### 1. Install PostgreSQL

Make sure PostgreSQL is installed and running on your system.

### 2. Initialize the Database

Run the SQL script to create the database and Quartz tables:

```bash
psql -U postgres -f database/init_postgres.sql
```

This will:
- Create a database named `quartz_demo`
- Create all required Quartz tables with the `QRTZ_` prefix
- Set up indexes for optimal performance

### 3. Configure Database Connection

The application is preconfigured to connect to PostgreSQL with these defaults:

- **URL**: `jdbc:postgresql://localhost:5432/quartz_demo`
- **Username**: `postgres`
- **Password**: `postgres`

If your PostgreSQL setup is different, edit `src/main/resources/quartz.properties`:

```properties
org.quartz.dataSource.quartzDS.URL = jdbc:postgresql://localhost:5432/quartz_demo
org.quartz.dataSource.quartzDS.user = your_username
org.quartz.dataSource.quartzDS.password = your_password
```

## Building

Build the project using Gradle:

```bash
./gradlew build
```

Or if you have Gradle installed globally:

```bash
gradle build
```

## Running

Run the application using Gradle:

```bash
./gradlew run
```

Or:

```bash
gradle run
```

The application will:
1. Start the Quartz scheduler
2. Schedule all demo jobs
3. Run for 60 seconds
4. Gracefully shutdown

## What You'll See

When running, you'll see output like:

```
============================================================
Quartz Scheduler Demo Application
============================================================
Scheduler: QuartzDemoScheduler
Instance ID: NON_CLUSTERED
============================================================

✓ Scheduled SimpleJob (runs every 10 seconds)
✓ Scheduled DataProcessingJob (runs every 15 seconds)
✓ Scheduled StatefulJob (runs every 8 seconds)
✓ Scheduled CronJob (runs every minute at 0 seconds)
Scheduler started! Jobs are now running...

[2025-10-10 21:45:00] SimpleJob 'simpleJob' executed!
  - Fire time: Thu Oct 10 21:45:00 AST 2025
  - Next fire time: Thu Oct 10 21:45:10 AST 2025

[2025-10-10 21:45:02] StatefulJob execution #1
  - This job maintains state between executions
  - Total executions so far: 1
...
```

## Project Structure

```
.
├── build.gradle                 # Gradle build configuration
├── settings.gradle              # Gradle settings
├── database/
│   └── init_postgres.sql        # PostgreSQL schema initialization
├── src/main/
│   ├── java/org/example/quartz/
│   │   ├── QuartzApp.java       # Main application
│   │   ├── SimpleJob.java       # Basic job example
│   │   ├── DataProcessingJob.java   # Job with parameters
│   │   └── StatefulJob.java     # Stateful job example
│   └── resources/
│       └── quartz.properties    # Quartz configuration
└── README.md
```

## Key Quartz Concepts Demonstrated

### 1. Job Scheduling

Jobs are scheduled using triggers. The app demonstrates:
- **SimpleScheduleBuilder**: For interval-based scheduling
- **CronScheduleBuilder**: For cron expression-based scheduling

### 2. JobDataMap

Pass parameters to jobs using `JobDataMap`:

```java
.usingJobData("department", "Engineering")
.usingJobData("batchSize", 100)
```

### 3. Stateful Jobs

Use `@PersistJobDataAfterExecution` to persist changes to the JobDataMap between executions.

### 4. JDBC JobStore

Jobs and triggers are persisted in PostgreSQL, allowing:
- Job recovery after application restarts
- Clustering support (when configured)
- Durable job storage

## Customization

### Modify Job Schedules

Edit the schedule methods in `QuartzApp.java`:

```java
// Change interval
.withIntervalInSeconds(10)  // Change to desired interval

// Change cron expression
.withSchedule(cronSchedule("0 0/5 * * * ?"))  // Every 5 minutes
```

### Add New Jobs

1. Create a new class implementing `org.quartz.Job`
2. Implement the `execute()` method
3. Add a scheduling method in `QuartzApp.java`
4. Call it from `main()`

### Adjust Runtime

Change the sleep duration in `QuartzApp.java`:

```java
Thread.sleep(60000); // Change to desired milliseconds
```

## Troubleshooting

### Database Connection Errors

Verify:
- PostgreSQL is running: `pg_isready`
- Database exists: `psql -U postgres -l | grep quartz_demo`
- Credentials in `quartz.properties` are correct

### Jobs Not Executing

Check:
- Quartz tables were created successfully
- No exceptions in the console output
- Thread pool configuration in `quartz.properties`

### Build Errors

Ensure:
- Java 21 is installed: `java -version`
- JAVA_HOME is set correctly
- Gradle can download dependencies (check network/proxy settings)

## Further Learning

- [Quartz Documentation](http://www.quartz-scheduler.org/documentation/)
- [Quartz Tutorials](http://www.quartz-scheduler.org/documentation/quartz-2.3.0/tutorials/)
- [Cron Expression Generator](https://www.freeformatter.com/cron-expression-generator-quartz.html)

## License

This is a demonstration project for educational purposes.
