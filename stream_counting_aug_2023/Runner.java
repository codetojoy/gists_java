
import java.util.*;
import java.util.stream.*;

class Result {
    private final String state;
    Result(String state) { this.state = state; }
    String getState() { return state; } 
}

public class Runner { 
    List<Result> buildList() {
        List<Result> results = new ArrayList<>();

        int max = 14;

        for (int i = 0; i <= max; i++) {
            String state = "" + (i % 6);
            results.add(new Result(state));
        }

        return results;
    }

    void run() {
        var results = buildList();
        var countMap = results.stream().collect(Collectors.groupingBy(Result::getState, Collectors.counting()));
        for (var entry : countMap.entrySet()) {
            System.out.println("TRACER for " + entry.getKey() + " # : " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        new Runner().run();
    }
}

