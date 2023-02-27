package com.api.employee.service;

import com.api.employee.dto.Employee;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface EmployeeService {
    List<Employee> getEmployees() throws IOException;
    Employee getEmployee(int id) throws IOException;
}
