
package net.codetojoy;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.type.TypeReference;

class Attendee {
    public String id;
    public String name;
}

class Attendees {
    public List<Attendee> attendees;
}

public class Runner {
    public static List<Attendee> go_v1(File file) throws Exception {
        var objectMapper = new ObjectMapper();
        var attendees = objectMapper.readValue(file, new TypeReference<List<Attendee>>(){});
        return attendees;
    }

    public static List<Attendee> go_v2(String filename) throws Exception {
        var objectMapper = new ObjectMapper();
        var path = Path.of(filename);
        var jsonStr = Files.readString(path);
        var attendees = objectMapper.readValue(jsonStr, new TypeReference<List<Attendee>>(){});
        return attendees;
    }

    public static void main(String[] args) {
        System.out.println("TRACER hello from Runner");
        try {
            var filename = "/Users/measter/src/github/codetojoy/gists_java/jackson_apr_2023/data.json";
            var file = new File(filename);
            if (file.exists()) {
                System.out.println("TRACER file ok");
            }
            // var attendees = go_v1(file);
            var attendees = go_v2(filename);
            System.out.println("TRACER ---------");
            for (var attendee : attendees) {
                System.out.println("TRACER id: " + attendee.id + " name: " + attendee.name);
            }
            System.out.println("TRACER Victory.");
        } catch (Exception ex) {
            System.err.println("TRACER caught ex: " + ex.getMessage());
        }
        System.out.println("Ready.");
    }
}
