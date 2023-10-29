package com.rslakra.userservice.jobs;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author Rohtash Lakra
 * @created 12/5/22 22:25 PM
 */
public class UserTransmitter {

    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask() {
        System.out.println("Fixed delay task - " + System.currentTimeMillis() / 1000);
    }


}
