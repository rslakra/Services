package com.rslakra.alertservice.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author Rohtash Lakra
 * @created 12/5/22 22:25 PM
 */
public class AlertTransmitter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlertTransmitter.class);

    /**
     *
     */
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask() {
        LOGGER.debug("Fixed delay task: {}", System.currentTimeMillis() / 1000);
    }


}
