package com.rslakra.alertservice.jobs;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author Rohtash Lakra
 * @created 12/5/22 22:31 PM
 */
public class FixedRateWithInitialDelayTask {

    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void scheduleFixedRateWithInitialDelayTask() {
        long now = System.currentTimeMillis() / 1000;
        System.out.println("Fixed rate task with one second initial delay - " + now);
    }
}
