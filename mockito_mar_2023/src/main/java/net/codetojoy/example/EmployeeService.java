
package net.codetojoy.example;

import java.util.*;

public class EmployeeService {
    private EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    List<Employee> getEmployees(Integer... ids) {
        return Arrays.stream(ids)
                     .map(repository::getEmployee)
                     .toList();
    }

    List<Employee> saveEmployees(Employee... employees) {
        return Arrays.stream(employees)
                     .map(repository::saveEmployee)
                     .toList();
    }
}
