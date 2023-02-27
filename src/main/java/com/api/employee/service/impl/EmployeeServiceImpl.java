package com.api.employee.service.impl;

import com.api.employee.dto.Employee;
import com.api.employee.exception.ResourceNotFoundException;
import com.api.employee.service.EmployeeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeServiceImpl implements EmployeeService {

    private List<Employee> employees;

    /**
     * This method is used to return employee list
     * @return
     * @throws IOException
     */
    @Override
    public List<Employee> getEmployees() throws IOException {
        if(employees == null || employees.size() < 1){
            loadFileData();
        }
        return employees;
    }

    /**
     * This method is used to return specific employee as per the employee id
     * @param id
     * @return
     * @throws IOException
     */
    @Override
    public Employee getEmployee(int id) throws IOException {
        if(employees == null || employees.size() < 1){
            loadFileData();
        }
       Optional<Employee> emp = employees.stream().filter(employee-> employee.getId() == id).findAny();
        if(!emp.isPresent()){
            throw new ResourceNotFoundException("Employee not found for ID="+id);
        }
        return emp.get();
    }

    /**
     * This method is used to fetch employees data from json file and map into the employee list.
     * @throws IOException
     */
    private void loadFileData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = TypeReference.class.getResourceAsStream("/data/employee.json");
        employees = mapper.readValue(inputStream, new TypeReference<List<Employee>>() {});
        if(employees == null && employees.size() < 1){
            throw new ResourceNotFoundException("Employees not found");
        }
    }
}
