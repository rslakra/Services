package com.rslaka.employeeservice.dao;

import java.util.List;

import com.rslaka.employeeservice.model.Employee;

public interface EmployeeDao {

	/**
	 *
	 * @return
	 */
	List<Employee> getEmployees();

	/**
	 *
	 * @param employee
	 */
	void saveEmployee(Employee employee);
}
