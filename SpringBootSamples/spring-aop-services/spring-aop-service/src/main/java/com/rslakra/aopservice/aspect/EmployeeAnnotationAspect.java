package com.rslakra.aopservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

@Aspect
public class EmployeeAnnotationAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeAnnotationAspect.class);

    @Before("@annotation(com.rslakra.aopservice.aspect.Loggable)")
    public void logAdvice(JoinPoint joinPoint) {
        LOGGER.debug("+logAdvice({})", joinPoint);
        LOGGER.info("Calling [%s()] with arguments=%s", joinPoint.getSignature().getName(),
                    Arrays.toString(joinPoint.getArgs()));
        HttpServletRequest servletRequest = null;
        int servletRequestHash = 0;
        // get current request from the context
        servletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        servletRequestHash = Objects.hashCode(servletRequest);
        LOGGER.info("threadName: {}, servletRequestHash: {}, servletRequest's Hash:{}",
                    Thread.currentThread().getName(), servletRequestHash, servletRequest.hashCode());
        // missing request object
        if (Objects.isNull(servletRequest)) {
            LOGGER.error("HttpServletRequest object should not be null!");
            throw new RuntimeException("Request object should not be null!");
        }

        try {
            Long waitTime = Long.valueOf(2000l);
            LOGGER.error("Waiting for {} millis ...", waitTime);
            Thread.sleep(waitTime);
        } catch (InterruptedException ex) {
            LOGGER.error("Ignore me");
        }

        if (servletRequestHash != servletRequest.hashCode()) {
            LOGGER.error("threadName: {}, servletRequestHash: {}, servletRequest's Hash:{}",
                         Thread.currentThread().getName(), servletRequestHash, servletRequest.hashCode());
            throw new RuntimeException("Request hash should not change!");
        }

        LOGGER.debug("-logAdvice()");
    }
}
