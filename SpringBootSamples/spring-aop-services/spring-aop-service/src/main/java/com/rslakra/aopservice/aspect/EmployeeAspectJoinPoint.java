package com.rslakra.aopservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Aspect
public class EmployeeAspectJoinPoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeAspectJoinPoint.class);

    @Before("execution(public void com.rslakra.aopservice.model..set*(*))")
    public void loggingAdvice(JoinPoint joinPoint) {
        LOGGER.debug("+loggingAdvice({})", joinPoint);
        LOGGER.debug("Before running loggingAdvice on method={}", joinPoint);
        LOGGER.debug("Arguments Passed={}", Arrays.toString(joinPoint.getArgs()));
    }

    //Advice arguments, will be applied to bean methods with single String argument
    @Before("args(name)")
    public void logStringArguments(String name) {
        LOGGER.debug("logStringArguments({})", name);
    }
}
