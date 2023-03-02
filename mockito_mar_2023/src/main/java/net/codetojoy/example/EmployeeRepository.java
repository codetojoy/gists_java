
package net.codetojoy.example;

public interface EmployeeRepository {
    Employee getEmployee(int id);
    Employee saveEmployee(Employee employee);
}
