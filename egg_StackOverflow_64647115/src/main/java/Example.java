
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.type.*;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.*;
 
public class Example {
    String readText(String filename) throws Exception {
        String result = null;

        try(FileInputStream inputStream = new FileInputStream(filename)) {
            result = IOUtils.toString(inputStream);
            System.out.println("TRACER size : " + result.length());
        }

        return result;
    }

    HashMap<String, String> go(String text) throws Exception {
        var objectMapper = new ObjectMapper();
        var jsonNode = objectMapper.readTree(text);
        var jsonStr = jsonNode.toString();

        var resultMap = objectMapper.readValue(jsonStr, 
                            new TypeReference<HashMap<String, String>>() {});

        return resultMap;
    }

    public static void main(String[] args) throws Exception {
        var example = new Example();
        String text = example.readText(args[0]);
        var map = example.go(text);

        for (var key : map.keySet()) {
            String value = map.get(key);
            System.out.println("TRACER key: " + key + " value: " + value);
        } 

        System.out.println("Ready.");
    }
}
