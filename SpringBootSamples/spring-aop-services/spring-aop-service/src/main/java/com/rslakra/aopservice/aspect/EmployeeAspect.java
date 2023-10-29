package com.rslakra.aopservice.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class EmployeeAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeAspect.class);

    @Before("execution(public String getName())")
    public void getNameAdvice() {
        LOGGER.debug("Executing Advice on getName()");
    }

    @Before("execution(* com.rslakra.aopservice.service.*.get*())")
    public void getAllAdvice() {
        LOGGER.debug("Service method getter called");
    }
}
