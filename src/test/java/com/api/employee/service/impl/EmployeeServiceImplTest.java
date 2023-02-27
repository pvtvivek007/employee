package com.api.employee.service.impl;

import com.api.employee.dto.Employee;
import com.api.employee.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    @Mock
    List<Employee> employees;

    @Test
    public void getEmployeesTest() throws IOException {
        Mockito.when(employees.size()).thenReturn(0);
        List<Employee> employeesList = employeeServiceImpl.getEmployees();
        Assertions.assertEquals(2,employeesList.size());
    }

    @Test
    public void getEmployeeTest() throws IOException {
        Mockito.when(employees.size()).thenReturn(0);
        Employee employee = employeeServiceImpl.getEmployee(1);
        Assertions.assertEquals(1,employee.getId());
    }

    @Test
    public void testResourceNotFoundException() throws IOException {
        int id = 3;
        ResourceNotFoundException resourceNotFoundException = Assertions.assertThrows(ResourceNotFoundException.class,
                ()->{employeeServiceImpl.getEmployee(id);} );
        Assertions.assertEquals("Employee not found for ID="+id,resourceNotFoundException.getMessage());
    }

}
