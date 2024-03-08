
package net.codetojoy;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown=true)
class Employee {
    @JsonProperty("employee_id")
    public int id;
    public String name;
    private String department;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; } 

    public String getName() { return name; }
    public void setName(String name) { this.name = name; } 

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; } 
}

public class Runner {
    private static final TypeReference<Employee> typeRef = new TypeReference<Employee>(){};

    public static Employee run(String filename) throws Exception {
        var objectMapper = new ObjectMapper();
        var path = Path.of(filename);
        var jsonStr = Files.readString(path);
        var employee = objectMapper.readValue(jsonStr, typeRef);
        return employee;
    }

    public static void main(String[] args) {
        if (args.length < 1) { throw new IllegalArgumentException("check args"); } 
        System.out.println("TRACER hello from Runner");
        try {
            var filename = args[0];
            var file = new File(filename);
            if (file.exists()) {
                System.out.println("TRACER file ok");
            } else {
                throw new IllegalArgumentException("illegal file"); 
            }
            var employee = run(filename);
            System.out.println("TRACER ---------");
            var message = new StringBuilder();
            message.append(" id: " + employee.getId());
            message.append(" name: " + employee.getName());
            message.append(" dept: " + employee.getDepartment());
            System.out.println("TRACER " + message.toString());
            System.out.println("TRACER Victory.");
        } catch (Exception ex) {
            System.err.println("TRACER caught ex: " + ex.getMessage());
        }
        System.out.println("Ready.");
    }
}
