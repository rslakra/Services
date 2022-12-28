package com.rslaka.employeeservice.service;

import com.rslaka.employeeservice.dao.EmployeeDao;
import com.rslaka.employeeservice.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    /**
     * @return
     */
    public List<Employee> getEmployees() {
        List<Employee> employees = employeeDao.getEmployees();
        return employees;
    }

    /**
     * @param employee
     */
    @Override
    public void saveEmployee(Employee employee) {
        employeeDao.saveEmployee(employee);
    }

}
