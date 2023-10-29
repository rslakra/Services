package com.rslakra.dockermicroservice.employee.service;

import com.rslakra.dockermicroservice.employee.domain.Employee;
import com.rslakra.dockermicroservice.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author Rohtash Lakra
 * @created 12/2/22 12:41 PM
 */
@Service
public class EmployeeService {

    @Resource
    private EmployeeRepository employeeRepository;

    @PostConstruct
    public void init() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Rohtash"));
        employees.add(new Employee("Singh"));
        employees.add(new Employee("Lakra"));
        employees.add(new Employee("Sangita"));
        employees = employeeRepository.saveAll(employees);
        System.out.println("Employees:" + employees);
    }

    /**
     * @return
     */
    public Collection<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

}
