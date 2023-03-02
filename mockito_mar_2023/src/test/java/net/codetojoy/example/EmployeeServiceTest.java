
package net.codetojoy.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;

import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class) 
public class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService service;

    @Mock
    private EmployeeRepository repository;

    @Test
    void getEmployeesBasic() {
        var ids = new Integer[] {5150, 6160};
        var edward = new Employee(5150, "EVH", "guitar");
        var alex = new Employee(6160, "AVH", "drums");

        when(repository.getEmployee(5150)).thenReturn(edward);
        when(repository.getEmployee(6160)).thenReturn(alex);

        // test
        var employees = service.getEmployees(ids);
    
        assertEquals(2, employees.size());
    }
}
