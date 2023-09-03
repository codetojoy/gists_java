
import java.util.*;
import java.util.stream.*;

public class R2 {
    private Optional<String> getData(String s) {
        return Optional.ofNullable(s);
    }

    void go() {
        List<String> strings = new ArrayList<>();

        strings.add("abc");
        strings.add(null);
        strings.add("def");
        strings.add(null);
        strings.add("ijk");
        strings.add(null);

        var results = strings.stream()
                             .map(this::getData)
                             .flatMap(Optional::stream)
                             .collect(Collectors.toList());

        for (String result : results) {
            System.out.println("TRACER result: " + result);
        }
    }

    public static void main(String[] args) {
        var runner = new R2();
        runner.go();
    }
}
