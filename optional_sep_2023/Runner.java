
import java.util.*;

public class Runner {
    private Optional<String> getData(String s) {
        return Optional.ofNullable(s);
    }

    public static void main(String[] args) {
        var runner = new Runner();
        var result1 = runner.getData("foobar");
        if (result1.isPresent()) {
            System.out.println("TRACER r1: " + result1.get());
        }

        var result2 = runner.getData(null);
        if (result2.isEmpty()) {
            System.out.println("TRACER r2 is null");
        }
    }
}
