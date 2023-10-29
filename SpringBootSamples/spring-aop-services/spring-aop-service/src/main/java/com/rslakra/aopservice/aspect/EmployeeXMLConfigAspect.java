package com.rslakra.aopservice.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeXMLConfigAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeXMLConfigAspect.class);

    public Object employeeAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        LOGGER.debug("+employeeAroundAdvice({})", proceedingJoinPoint);
        LOGGER.debug("EmployeeXMLConfigAspect:: Before invoking getName() method");
        Object response = null;
        try {
            response = proceedingJoinPoint.proceed();
        } catch (Throwable ex) {
            LOGGER.error(ex.getLocalizedMessage(), ex);
        }

        LOGGER.debug(
            "-employeeAroundAdvice(), EmployeeXMLConfigAspect:: After invoking getName() method. Return response={}",
            response);
        return response;
    }
}
