package com.rslaka.employeeservice.controller;

import com.rslaka.employeeservice.model.Employee;
import com.rslaka.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * @return
     */
    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();

    }

    /**
     * @param employee
     */
    @PostMapping
    public void insertEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
    }

}
