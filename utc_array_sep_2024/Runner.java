
import java.util.*;

import java.time.*;
import java.time.format.*;
import java.time.temporal.*;

class ArrayInfo {
    final LocalDateTime dateTime;
    final int value;

    ArrayInfo(LocalDateTime dateTime, int value) {
        this.dateTime = dateTime;
        this.value = value;
    }

    public String toString() {
        return "dateTime: " + dateTime + ", value: " + value;
    }
}

class ArrayInfoComparator implements Comparator<ArrayInfo> {
    private int getHour(ArrayInfo arrayInfo) {
        LocalDateTime utcDateTime = arrayInfo.dateTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
        return utcDateTime.getHour();
    }

    public int compare(ArrayInfo infoA, ArrayInfo infoB) {
        int utcHourA = getHour(infoA);
        int utcHourB = getHour(infoB);
        return Integer.compare(utcHourA, utcHourB);
    }
}

public class Runner {
    private static final String TIMESTAMP_FORMAT = "2024-09-01T%02d:00:00";
    private List<ArrayInfo> localTimeArray = new ArrayList<>();

    private void buildLocalArray() {
        int max = 24;
        int i = 0;

        while (i < max) {
            String timestampStr = String.format(TIMESTAMP_FORMAT, i);
            LocalDateTime dateTime = LocalDateTime.parse(timestampStr);
            ArrayInfo arrayInfo = new ArrayInfo(dateTime, i);
            localTimeArray.add(arrayInfo);
            i++;
        }
    }

    private void printLocalArray(String banner) {
        System.out.println("-------------------------------");
        System.out.println(banner + " :\n");
        
        for (var arrayInfo : localTimeArray) {
            System.out.println("TRACER : " + arrayInfo.toString());
        }
    }

    private void sortLocalArray() {
        Collections.sort(localTimeArray, new ArrayInfoComparator());
    }

    public static void main(String[] args) {
        Runner runner = new Runner();

        runner.buildLocalArray();
        runner.printLocalArray("local time"); 
        runner.sortLocalArray();
        runner.printLocalArray("UTC equivalent"); 

        System.out.println("Ready.");
    }
}
