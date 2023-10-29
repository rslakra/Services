package com.rslakra.aopservice.service;

import com.rslakra.aopservice.model.Employee;

public class EmployeeService {

    private Employee employee;

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
