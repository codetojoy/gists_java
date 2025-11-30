package net.codetojoy.schedule;

public class DayValidator {
    public boolean validate(Day day) {
        if (day == null) {
            return false;
        }

        if (day.getConfig() == null) {
            return false;
        }

        if (day.getConfig() instanceof CustomRangeConfig customRangeConfig) {
            return validateCustomRangeConfig(customRangeConfig);
        }

        return true;
    }

    private boolean validateCustomRangeConfig(CustomRangeConfig config) {
        if (config.getCustomRange() == null) {
            return false;
        }

        CustomRange customRange = config.getCustomRange();

        if (customRange.getStartTime() == null || customRange.getEndTime() == null) {
            return false;
        }

        Time startTime = customRange.getStartTime();
        Time endTime = customRange.getEndTime();

        if (!isValidTime(startTime) || !isValidTime(endTime)) {
            return false;
        }

        return true;
    }

    private boolean isValidTime(Time time) {
        if (time == null) {
            return false;
        }

        int hour = time.getHour();
        int minute = time.getMinute();

        if (hour < 0 || hour > 23) {
            return false;
        }

        if (minute < 0 || minute > 59) {
            return false;
        }

        return true;
    }
}
