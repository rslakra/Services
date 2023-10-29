package com.rslakra.aopservice;

import com.rslakra.aopservice.model.Employee;
import com.rslakra.aopservice.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AopService.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        LOGGER.debug("+main({})", args);
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        EmployeeService employeeService = ctx.getBean("employeeService", EmployeeService.class);
        Employee employee = employeeService.getEmployee();
        LOGGER.debug(employee.getName());

        // change name
        employee.setName("Lakra");
        LOGGER.debug(employee.getName());
        employee.throwException();

        ctx.close();
        LOGGER.debug("-main()");
    }

}
