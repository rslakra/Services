package com.rslakra.aopservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class EmployeeAfterAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeAfterAspect.class);

    @After("args(name)")
    public void logStringArguments(String name) {
        LOGGER.debug("Running After Advice. String argument passed={}", name);
    }

    @AfterThrowing("within(com.rslakra.aopservice.model.Employee)")
    public void logExceptions(JoinPoint joinPoint) {
        LOGGER.debug("Exception thrown in Employee Method={}", joinPoint.toString());
    }

    @AfterReturning(pointcut = "execution(* getName())", returning = "returnString")
    public void getNameReturningAdvice(String returnString) {
        LOGGER.debug("getNameReturningAdvice executed. Returned String={}", returnString);
    }

}
