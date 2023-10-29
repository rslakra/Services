package com.rslakra.springbootsamples.aoparoundadvice.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Rohtash Lakra
 * @created 1/20/22 5:23 PM
 */
@Aspect
@Component
public class LogMethodExecutionAspect {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(LogMethodExecutionAspect.class);

    /**
     * Create our pointcut and advice.
     * <p>
     * Our pointcut just says, ‘Apply this advice any method which is annotated with @LogMethodExecutionTime.'
     * <p>
     * The method logMethodExecutionTime() itself is our advice. There is a single argument, ProceedingJoinPoint. In our
     * case, this will be an executing method which has been annotated with @LogMethodExecutionTime.
     * <p>
     * Finally, when our annotated method ends up being called, what will happen is our advice will be called first.
     * Then it's up to our advice to decide what to do next. In our case, our advice is doing nothing other than calling
     * proceed(), which is the just calling the original annotated method.
     * <p>
     * Again, we've not done anything that's particularly complicated here. We've just recorded the current time,
     * executed the method, then printed the amount of time it took to the console. We're also logging the method
     * signature, which is provided to use the joinpoint instance. We would also be able to gain access to other bits of
     * information if we wanted to, such as method arguments.
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("@annotation(com.rslakra.springbootsamples.aoparoundadvice.annotation.LogMethodExecutionTime)")
//    @Before("@annotation(com.rslakra.springaspects.annotation.LogMethodExecutionTime)")
    public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.debug("+logMethodExecutionTime()");
        final Signature signature = joinPoint.getSignature();
//        LOGGER.info("getDeclaringType:{}, getDeclaringTypeName:{}, getName:{}", signature.getDeclaringType(),
//                    signature.getDeclaringTypeName(), signature.getName());
        long start = System.currentTimeMillis();
        final Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        LOGGER.info("[{}.{}()] method took:{}ms", signature.getDeclaringTypeName(), signature.getName(), executionTime);
        LOGGER.debug("-logMethodExecutionTime()");
        return proceed;
    }

}
