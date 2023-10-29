package com.rslakra.aopservice.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class EmployeeAroundAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeAroundAspect.class);


    @Around("execution(* com.rslakra.aopservice.model.Employee.getName())")
    public Object employeeAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        LOGGER.debug("+employeeAroundAdvice({}) - Before invoking getName() method", proceedingJoinPoint);
        Object response = null;
        try {
            response = proceedingJoinPoint.proceed();
        } catch (Throwable ex) {
            LOGGER.error(ex.getLocalizedMessage(), ex);
        }

        LOGGER.debug("-employeeAroundAdvice(), After invoking getName() method. Return response={}", response);
        return response;
    }
}
