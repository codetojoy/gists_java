# Jackson Scheduling Project - Development Session

## Project Overview
This session involved creating a Java project with Jackson-annotated classes for managing schedules with custom time ranges.

## Package Structure
```
net.codetojoy
├── Runner (main class)
└── schedule
    ├── Schedule
    ├── Day
    ├── DayConfig (abstract)
    ├── IncludeAllDayConfig
    ├── ExcludeAllDayConfig
    ├── CustomRangeConfig
    ├── CustomRange
    └── Time
```

## Classes Created

### 1. Runner (`net.codetojoy.Runner`)
- Main class that demonstrates the scheduling system
- Creates a `Schedule` with three days: sunday, saturday, and weekDay
- Sunday and Saturday use `IncludeAllDayConfig`
- WeekDay uses `CustomRangeConfig` with a time range from 9:00 to 17:00
- Serializes the schedule to pretty-printed JSON using Jackson's `ObjectMapper`
- Prints "TRACER Ready." followed by the JSON output

### 2. Schedule (`net.codetojoy.schedule.Schedule`)
- Represents a weekly schedule
- **Fields**:
  - `sunday` (Day)
  - `saturday` (Day)
  - `weekDay` (Day)
- Annotated with `@JsonIgnoreProperties(ignoreUnknown = true)`
- All fields annotated with `@JsonProperty`

### 3. Day (`net.codetojoy.schedule.Day`)
- Represents a single day's configuration
- **Fields**:
  - `config` (DayConfig) - polymorphic field that can be one of three types
- Annotated with `@JsonIgnoreProperties(ignoreUnknown = true)`
- Uses Jackson polymorphic type handling to ensure only one configuration type is present

### 4. DayConfig (`net.codetojoy.schedule.DayConfig`)
- Abstract base class for day configurations
- Uses `@JsonTypeInfo` with discriminator property "type"
- Uses `@JsonSubTypes` to register three implementations:
  - `IncludeAllDayConfig` (type: "includeAllDay")
  - `ExcludeAllDayConfig` (type: "excludeAllDay")
  - `CustomRangeConfig` (type: "customRange")
- Ensures mutual exclusivity - only one configuration type can exist per Day

### 5. IncludeAllDayConfig (`net.codetojoy.schedule.IncludeAllDayConfig`)
- Extends `DayConfig`
- Represents a day that includes all hours
- No additional fields
- Type discriminator: "includeAllDay"

### 6. ExcludeAllDayConfig (`net.codetojoy.schedule.ExcludeAllDayConfig`)
- Extends `DayConfig`
- Represents a day that excludes all hours
- No additional fields
- Type discriminator: "excludeAllDay"

### 7. CustomRangeConfig (`net.codetojoy.schedule.CustomRangeConfig`)
- Extends `DayConfig`
- Represents a day with a custom time range
- **Fields**:
  - `customRange` (CustomRange)
- Type discriminator: "customRange"

### 8. CustomRange (`net.codetojoy.schedule.CustomRange`)
- Represents a custom time range
- **Fields**:
  - `startTime` (Time)
  - `endTime` (Time)
- Annotated with `@JsonIgnoreProperties(ignoreUnknown = true)`
- All fields annotated with `@JsonProperty`

### 9. Time (`net.codetojoy.schedule.Time`)
- Represents a specific time
- **Fields**:
  - `hour` (int)
  - `minute` (int)
- Annotated with `@JsonIgnoreProperties(ignoreUnknown = true)`
- All fields annotated with `@JsonProperty`

## Key Decisions

### Package Refactoring
- Initially created with package `com.example`
- Refactored to `net.codetojoy` throughout the entire project
- Updated `build.gradle` group and mainClass references
- Moved directory structure from `src/main/java/com/example` to `src/main/java/net/codetojoy`

### Jackson Configuration
- All classes use `@JsonIgnoreProperties(ignoreUnknown = true)` for flexibility
- All fields use `@JsonProperty` annotations with explicit property names
- Standard getter/setter pattern for all fields

### Polymorphic Type Handling (Union Type Refactoring)
- Refactored `Day` class to use a union type pattern
- Implemented using Jackson's `@JsonTypeInfo` and `@JsonSubTypes` annotations
- Created abstract `DayConfig` base class with three concrete implementations
- Ensures mutual exclusivity - only one configuration type can exist per Day
- Uses "type" as the discriminator field in JSON
- Type-safe approach that prevents invalid states at runtime

### Example Data Structure
The current `Runner` creates this structure:
```java
Schedule
├── sunday: Day {
    config: IncludeAllDayConfig { type: "includeAllDay" }
}
├── saturday: Day {
    config: IncludeAllDayConfig { type: "includeAllDay" }
}
└── weekDay: Day {
    config: CustomRangeConfig {
        type: "customRange",
        customRange: CustomRange {
            startTime: Time { hour: 9, minute: 0 },
            endTime: Time { hour: 17, minute: 0 }
        }
    }
}
```

### Example JSON Output
```json
{
  "sunday": {
    "config": {
      "type": "includeAllDay"
    }
  },
  "saturday": {
    "config": {
      "type": "includeAllDay"
    }
  },
  "weekDay": {
    "config": {
      "type": "customRange",
      "customRange": {
        "startTime": {
          "hour": 9,
          "minute": 0
        },
        "endTime": {
          "hour": 17,
          "minute": 0
        }
      }
    }
  }
}
```

## Dependencies
- Jackson Core: 2.18.1
- Jackson Databind: 2.18.1
- Jackson Annotations: 2.18.1
- Java 21

## Build Configuration
- Gradle project
- Application plugin configured
- Main class: `net.codetojoy.Runner`
- Group: `net.codetojoy`
- Version: 1.0-SNAPSHOT
