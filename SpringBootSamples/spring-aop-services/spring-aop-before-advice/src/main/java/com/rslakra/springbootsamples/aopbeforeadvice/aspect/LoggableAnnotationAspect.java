package com.rslakra.springbootsamples.aopbeforeadvice.aspect;

import com.devamatre.framework.spring.aop.AopUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LoggableAnnotationAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggableAnnotationAspect.class);

    private HttpServletRequest servletRequest = null;
    private int servletRequestHash = 0;

    /**
     * @param joinPoint
     */
    @Before("@annotation(com.rslakra.springbootsamples.aopbeforeadvice.aspect.Loggable)")
    public void logAdvice(JoinPoint joinPoint) {
        LOGGER.debug("+logAdvice({})", joinPoint);
        LOGGER.info("Calling [{}()] with arguments={}", joinPoint.getSignature().getName(),
                    Arrays.toString(joinPoint.getArgs()));
//        HttpServletRequest servletRequest = null;
//        int servletRequestHash = 0;

        // get current request from the context
        servletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        servletRequestHash = Objects.hashCode(servletRequest);
        LOGGER.info("threadName: {}, servletRequestHash: {}, servletRequest's Hash:{}",
                    Thread.currentThread().getName(), servletRequestHash, servletRequest.hashCode());
        // missing request object
        if (Objects.isNull(servletRequest)) {
            LOGGER.error("servletRequest object should not be null!");
            throw new RuntimeException("Request object should not be null!");
        }

        Loggable loggable = AopUtils.getAnnotationByClass(joinPoint, Loggable.class);
        LOGGER.error("loggable: {}", loggable);
        if (Objects.isNull(loggable)) {
            Method method = AopUtils.findMethod(joinPoint);
            String logMessage = "Loggable annotation is not applied to " + method.getName() + " method!";
            LOGGER.error(logMessage);
            throw new RuntimeException(logMessage);
        }

        if (loggable.localOnly() && !AopUtils.isLocalHostRequest(servletRequest)) {
            throw new RuntimeException("Only allowed to localhost!");
        }

        AtomicInteger atomicInteger = new AtomicInteger(1);
        while (atomicInteger.get() < 10) {
            try {
                Long waitTime = Long.valueOf(100L);
                LOGGER.error("Waiting for {} millis ...", waitTime);
                Thread.sleep(waitTime);
                atomicInteger.incrementAndGet();
            } catch (InterruptedException ex) {
                LOGGER.error("Ignore me");
            }
        }

        if (servletRequestHash != servletRequest.hashCode()) {
            LOGGER.error("threadName: {}, servletRequestHash: {}, servletRequest's Hash:{}",
                         Thread.currentThread().getName(), servletRequestHash, servletRequest.hashCode());
            throw new RuntimeException("Request hash should not change!");
        }

        LOGGER.debug("-logAdvice()");
    }
}
