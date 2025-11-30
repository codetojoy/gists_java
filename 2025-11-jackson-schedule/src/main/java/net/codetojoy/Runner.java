package net.codetojoy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.codetojoy.schedule.CustomRange;
import net.codetojoy.schedule.CustomRangeConfig;
import net.codetojoy.schedule.Day;
import net.codetojoy.schedule.ExcludeAllDayConfig;
import net.codetojoy.schedule.IncludeAllDayConfig;
import net.codetojoy.schedule.Schedule;
import net.codetojoy.schedule.Time;

public class Runner {
    public static void main(String[] args) throws JsonProcessingException {
        System.out.println("TRACER Ready.");

        Time startTime = new Time();
        startTime.setHour(9);
        startTime.setMinute(0);

        Time endTime = new Time();
        endTime.setHour(17);
        endTime.setMinute(0);

        CustomRange customRange = new CustomRange();
        customRange.setStartTime(startTime);
        customRange.setEndTime(endTime);

        CustomRangeConfig customRangeConfig = new CustomRangeConfig();
        customRangeConfig.setCustomRange(customRange);

        Day sunday = new Day();
        sunday.setConfig(new ExcludeAllDayConfig());

        Day saturday = new Day();
        saturday.setConfig(new IncludeAllDayConfig());

        Day weekDay = new Day();
        weekDay.setConfig(customRangeConfig);

        Schedule schedule = new Schedule();
        schedule.setSunday(sunday);
        schedule.setSaturday(saturday);
        schedule.setWeekDay(weekDay);

        ObjectMapper mapper = new ObjectMapper();
        String prettyString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(schedule);
        System.out.println(prettyString);
    }
}
