package net.codetojoy.schedule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DayValidatorUnitTests {
    private DayValidator validator;

    @BeforeEach
    void setUp() {
        validator = new DayValidator();
    }

    @Test
    void testValidate_NullDay_ReturnsFalse() {
        boolean result = validator.validate(null);
        assertFalse(result);
    }

    @Test
    void testValidate_DayWithNullConfig_ReturnsFalse() {
        Day day = new Day();
        day.setConfig(null);

        boolean result = validator.validate(day);
        assertFalse(result);
    }

    @Test
    void testValidate_IncludeAllDayConfig_ReturnsTrue() {
        Day day = new Day();
        day.setConfig(new IncludeAllDayConfig());

        boolean result = validator.validate(day);
        assertTrue(result);
    }

    @Test
    void testValidate_ExcludeAllDayConfig_ReturnsTrue() {
        Day day = new Day();
        day.setConfig(new ExcludeAllDayConfig());

        boolean result = validator.validate(day);
        assertTrue(result);
    }

    @Test
    void testValidate_ValidCustomRangeConfig_ReturnsTrue() {
        Time startTime = new Time();
        startTime.setHour(9);
        startTime.setMinute(0);

        Time endTime = new Time();
        endTime.setHour(17);
        endTime.setMinute(30);

        CustomRange customRange = new CustomRange();
        customRange.setStartTime(startTime);
        customRange.setEndTime(endTime);

        CustomRangeConfig config = new CustomRangeConfig();
        config.setCustomRange(customRange);

        Day day = new Day();
        day.setConfig(config);

        boolean result = validator.validate(day);
        assertTrue(result);
    }

    @Test
    void testValidate_CustomRangeConfigWithNullCustomRange_ReturnsFalse() {
        CustomRangeConfig config = new CustomRangeConfig();
        config.setCustomRange(null);

        Day day = new Day();
        day.setConfig(config);

        boolean result = validator.validate(day);
        assertFalse(result);
    }

    @Test
    void testValidate_CustomRangeWithNullStartTime_ReturnsFalse() {
        Time endTime = new Time();
        endTime.setHour(17);
        endTime.setMinute(0);

        CustomRange customRange = new CustomRange();
        customRange.setStartTime(null);
        customRange.setEndTime(endTime);

        CustomRangeConfig config = new CustomRangeConfig();
        config.setCustomRange(customRange);

        Day day = new Day();
        day.setConfig(config);

        boolean result = validator.validate(day);
        assertFalse(result);
    }

    @Test
    void testValidate_CustomRangeWithNullEndTime_ReturnsFalse() {
        Time startTime = new Time();
        startTime.setHour(9);
        startTime.setMinute(0);

        CustomRange customRange = new CustomRange();
        customRange.setStartTime(startTime);
        customRange.setEndTime(null);

        CustomRangeConfig config = new CustomRangeConfig();
        config.setCustomRange(customRange);

        Day day = new Day();
        day.setConfig(config);

        boolean result = validator.validate(day);
        assertFalse(result);
    }

    @Test
    void testValidate_InvalidHourNegative_ReturnsFalse() {
        Time startTime = new Time();
        startTime.setHour(-1);
        startTime.setMinute(0);

        Time endTime = new Time();
        endTime.setHour(17);
        endTime.setMinute(0);

        CustomRange customRange = new CustomRange();
        customRange.setStartTime(startTime);
        customRange.setEndTime(endTime);

        CustomRangeConfig config = new CustomRangeConfig();
        config.setCustomRange(customRange);

        Day day = new Day();
        day.setConfig(config);

        boolean result = validator.validate(day);
        assertFalse(result);
    }

    @Test
    void testValidate_InvalidHourTooLarge_ReturnsFalse() {
        Time startTime = new Time();
        startTime.setHour(9);
        startTime.setMinute(0);

        Time endTime = new Time();
        endTime.setHour(24);
        endTime.setMinute(0);

        CustomRange customRange = new CustomRange();
        customRange.setStartTime(startTime);
        customRange.setEndTime(endTime);

        CustomRangeConfig config = new CustomRangeConfig();
        config.setCustomRange(customRange);

        Day day = new Day();
        day.setConfig(config);

        boolean result = validator.validate(day);
        assertFalse(result);
    }

    @Test
    void testValidate_InvalidMinuteNegative_ReturnsFalse() {
        Time startTime = new Time();
        startTime.setHour(9);
        startTime.setMinute(-1);

        Time endTime = new Time();
        endTime.setHour(17);
        endTime.setMinute(0);

        CustomRange customRange = new CustomRange();
        customRange.setStartTime(startTime);
        customRange.setEndTime(endTime);

        CustomRangeConfig config = new CustomRangeConfig();
        config.setCustomRange(customRange);

        Day day = new Day();
        day.setConfig(config);

        boolean result = validator.validate(day);
        assertFalse(result);
    }

    @Test
    void testValidate_InvalidMinuteTooLarge_ReturnsFalse() {
        Time startTime = new Time();
        startTime.setHour(9);
        startTime.setMinute(0);

        Time endTime = new Time();
        endTime.setHour(17);
        endTime.setMinute(60);

        CustomRange customRange = new CustomRange();
        customRange.setStartTime(startTime);
        customRange.setEndTime(endTime);

        CustomRangeConfig config = new CustomRangeConfig();
        config.setCustomRange(customRange);

        Day day = new Day();
        day.setConfig(config);

        boolean result = validator.validate(day);
        assertFalse(result);
    }

    @Test
    void testValidate_BoundaryHour0_ReturnsTrue() {
        Time startTime = new Time();
        startTime.setHour(0);
        startTime.setMinute(0);

        Time endTime = new Time();
        endTime.setHour(23);
        endTime.setMinute(59);

        CustomRange customRange = new CustomRange();
        customRange.setStartTime(startTime);
        customRange.setEndTime(endTime);

        CustomRangeConfig config = new CustomRangeConfig();
        config.setCustomRange(customRange);

        Day day = new Day();
        day.setConfig(config);

        boolean result = validator.validate(day);
        assertTrue(result);
    }

    @Test
    void testValidate_BoundaryHour23_ReturnsTrue() {
        Time startTime = new Time();
        startTime.setHour(9);
        startTime.setMinute(0);

        Time endTime = new Time();
        endTime.setHour(23);
        endTime.setMinute(59);

        CustomRange customRange = new CustomRange();
        customRange.setStartTime(startTime);
        customRange.setEndTime(endTime);

        CustomRangeConfig config = new CustomRangeConfig();
        config.setCustomRange(customRange);

        Day day = new Day();
        day.setConfig(config);

        boolean result = validator.validate(day);
        assertTrue(result);
    }
}
