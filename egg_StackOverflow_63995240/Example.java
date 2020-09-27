
import java.util.*;
import java.util.regex.*;

// requires JDK 11+

public class Example {
    private static final String ASTERISK = "*";
    
    String replace(String regex, String replacement, String input) {
        String result = "N/A";

        Matcher m = Pattern.compile(regex).matcher(input);
        if (m.find()) {
            int groupToReplace = 1;
            result = new StringBuilder(input).replace(m.start(groupToReplace), m.end(groupToReplace), replacement).toString();
        } else {
            throw new IllegalStateException("internal error");
        }

        return result;
    }

    String maskEmailId(String input) {
        int numChars = input.length();
        String regex = "";
        String replacement = "";

        // TODO: the regex strings can be compiled into proper Pattern objects
        if (numChars == 2) {
            regex = ".(.)";
            replacement = ASTERISK;
        } else if (numChars == 3) {
            regex = ".(.).";
            replacement = ASTERISK;
        } else if (numChars == 4) {
            regex = ".(..).";
            replacement = ASTERISK + ASTERISK;
        } else {
            regex = "..(.*)..";
            int numAsterisks = numChars - 4;

            // requires JDK 11+
            replacement = ASTERISK.repeat(numAsterisks);
        }

        String result = replace(regex, replacement, input);

        return result;
    }

    String maskEmailAddress(String input) {
        String[] tokens = input.split("@");
        String frontToken = tokens[0];
        String backToken = tokens[1];
        return maskEmailId(frontToken) + "@" + backToken;
    }

    void runExample(String input, String expected) {
        String result = maskEmailAddress(input);

        if (! result.equals(expected)) {
            throw new IllegalStateException("for input: '" + input + 
                                            "' expected: '" + expected + "'");
        }
        System.out.println("TRACER result: " + result);
    }

    public static void main(String... args) {
        var example = new Example();

        var inputMap = Map.of(
                        "qwerty@gmail.com","qw**ty@gmail.com",
                        "helloworld@gmail.com","he******ld@gmail.com",
                        "stackoverflow@gmail.com","st*********ow@gmail.com",
                        "abcde@gmail.com","ab*de@gmail.com",
                        "abcd@gmail.com","a**d@gmail.com",
                        "abc@gmail.com","a*c@gmail.com",
                        "ab@gmail.com","a*@gmail.com"
                        );

        for (String input : inputMap.keySet()) {
            String expected = inputMap.get(input);
            example.runExample(input, expected); 
        }
    }
}

