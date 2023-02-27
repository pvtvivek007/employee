package com.api.employee.controller;

import com.api.employee.dto.Employee;
import com.api.employee.exception.ResourceNotFoundException;
import com.api.employee.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @Test
    public void getEmployeesTest() throws IOException {
        Mockito.when(employeeService.getEmployees()).thenReturn(new ArrayList<Employee>());
        ResponseEntity<List<Employee>> response = employeeController.getEmployees();
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void getEmployeeTest() throws IOException {
        Mockito.when(employeeService.getEmployee(Mockito.anyInt())).thenReturn(new Employee());
        ResponseEntity<Employee> response = employeeController.getEmployee(Mockito.anyInt());
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testResourceNotFoundException() throws IOException {
        Mockito.when(employeeService.getEmployee(Mockito.anyInt())).thenThrow(new ResourceNotFoundException("Employee Not found"));
        ResourceNotFoundException resourceNotFoundException = Assertions.assertThrows(ResourceNotFoundException.class,
                ()->{employeeController.getEmployee(Mockito.anyInt());} );
        Assertions.assertEquals("Employee Not found",resourceNotFoundException.getMessage());
    }

}
