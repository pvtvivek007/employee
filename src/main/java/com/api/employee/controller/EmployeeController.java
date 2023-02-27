package com.api.employee.controller;

import com.api.employee.dto.Employee;
import com.api.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * This is the request handler class for the employee api.
 */
@RestController
@RequestMapping("/v1.0")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * This request handler is used to return list of employees
     * @return
     * @throws IOException
     */
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        List<Employee> employees = employeeService.getEmployees();
        return new ResponseEntity<>(employees,headers, HttpStatus.OK);
    }

    /**
     * This request handler is used to return employee data as per the employee id.
     * @param id
     * @return
     * @throws IOException
     */

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int id) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(employeeService.getEmployee(id),headers, HttpStatus.OK);
    }

}
