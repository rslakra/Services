package com.rslaka.employeeservice.dao;

import com.rslaka.employeeservice.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Repository
public class EmployeeDaoImpl extends AbstractDao<Employee> implements EmployeeDao {

    private static final String TABLE = "employees";

    @Autowired
    private DataSource dataSource;

    /**
     *
     */
    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    /**
     * @return
     */
    @Override
    public List<Employee> getEmployees() {
        String sql = super.selectQuery(TABLE);
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Employee> result = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            Employee emp = new Employee();
            emp.setId((String) row.get("id"));
            emp.setName((String) row.get("name"));
            result.add(emp);
        }

        return result;
    }

    /**
     * @param employee
     */
    @Override
    public void saveEmployee(Employee employee) {
        String sql = super.insertQuery(TABLE, new String[]{"id", "name"});
        getJdbcTemplate().update(sql, new Object[]{employee.getId(), employee.getName()});
    }
}
