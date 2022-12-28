package com.rslaka.employeeservice.service;

import com.rslaka.employeeservice.model.Employee;

import java.util.List;

public interface EmployeeService {

    /**
     * @return
     */
    List<Employee> getEmployees();

    /**
     * @param employee
     */
    void saveEmployee(Employee employee);
}
