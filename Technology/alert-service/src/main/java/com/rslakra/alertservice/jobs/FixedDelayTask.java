package com.rslakra.alertservice.jobs;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * The fixedDelay property makes sure that there is a delay of n millisecond between the finish time of an execution of
 * a task and the start time of the next execution of the task.
 * <p>
 * This property is specifically useful when we need to make sure that only one instance of the task runs all the time.
 * For dependent jobs, it is quite helpful.
 *
 * @author Rohtash Lakra
 * @created 12/5/22 22:28 PM
 */
public class FixedDelayTask {

    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask() {
        System.out.println("Fixed delay task - " + System.currentTimeMillis() / 1000);
    }

}
