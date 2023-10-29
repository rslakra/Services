package com.rslakra.aopservice.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class EmployeeAspectPointcut {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeAspectPointcut.class);

    @Before("getNamePointcut()")
    public void loggingAdvice() {
        LOGGER.debug("loggingAdvice() - Executing loggingAdvice on getName()");
    }

    @Before("getNamePointcut()")
    public void secondAdvice() {
        LOGGER.debug("secondAdvice() - Executing secondAdvice on getName()");
    }

    @Pointcut("execution(public String getName())")
    public void getNamePointcut() {
        LOGGER.debug("getNamePointcut() - Executing getName()");
    }

    @Before("allMethodsPointcut()")
    public void allServiceMethodsAdvice() {
        LOGGER.debug("allServiceMethodsAdvice() -Before executing service method");
    }

    //Pointcut to execute on all the methods of classes in a package
    @Pointcut("within(com.rslakra.aopservice.service.*)")
    public void allMethodsPointcut() {
        LOGGER.debug("allMethodsPointcut()");
    }

}
