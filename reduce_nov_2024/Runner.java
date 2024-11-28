
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;

class Data {
    private String date;
    private int value;

    public Data(String date, int value) {
        this.date = date;
        this.value = value;
    }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }

    @Override
    public String toString() {
        return String.format("date: %s, value: %d", date, value);
    }
}

public class Runner {
    Data buildData(String date, int value) {
        return new Data(date, value);
    }

    List<Data> buildList() {
        List<Data> results = new ArrayList<>();
        results.add(buildData("2024-09-01", 11));

        results.add(buildData("2024-09-02", 12));
        results.add(buildData("2024-09-02", 12));

        results.add(buildData("2024-09-03", 13));
        results.add(buildData("2024-09-03", 13));
        results.add(buildData("2024-09-03", 13));

        results.add(buildData("2024-09-04", 14));
        results.add(buildData("2024-09-04", 14));
        results.add(buildData("2024-09-04", 14));
        results.add(buildData("2024-09-04", 14));
        return results;
    }

    Data combine(Data data1, Data data2) {
        // use date2 as first iteration, data1 will be identity
        String date2 = data2.getDate();

        int value1 = data1.getValue();
        int value2 = data2.getValue();
        Data result = new Data(date2, value1 + value2);
        return result;
    }

    private static final Data IDENTITY = new Data("", 0);

    /*
     *  example input: [{"2024-11-18", 10}, {"2024-11-18", 10}, {"2024-11-18", 10}]
     *  example output: stream of [{"2024-11-18", 30}]
     */
    Stream<Data> myReducer(List<Data> datasForDate) {
        return Stream.of(datasForDate.stream().reduce(IDENTITY, this::combine));
    }

    void run() {
        List<Data> datas = buildList();

        List<Data> results = datas.stream()
                                  .collect(groupingBy(Data::getDate))
                                  .entrySet()
                                  .stream()
                                  .flatMap(e -> myReducer(e.getValue()))
                                  .collect(toList());

        for (var result : results) {
            System.out.println("TRACER r : " + result);
        }
    }

    public static void main(String[] args) throws Exception {
        new Runner().run();
        System.out.println("Ready.");
    }
}
