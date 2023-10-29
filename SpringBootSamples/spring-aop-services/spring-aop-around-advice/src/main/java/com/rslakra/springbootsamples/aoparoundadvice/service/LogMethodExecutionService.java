package com.rslakra.springbootsamples.aoparoundadvice.service;

import com.rslakra.springbootsamples.aoparoundadvice.annotation.LogMethodExecutionTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Rohtash Lakra
 * @created 1/20/22 5:46 PM
 */
@Component
public class LogMethodExecutionService {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(LogMethodExecutionService.class);

    /**
     * @throws InterruptedException
     */
    @LogMethodExecutionTime
    public void logExecutionTime() throws InterruptedException {
        LOGGER.debug("logExecutionTime() - {}", new Date());
        Thread.sleep(1000);
    }

}
