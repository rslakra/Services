package com.rslakra.userservice.jobs;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * By default, Spring will use the server's local time zone for the cron expression. However, we can use the zone
 * attribute to change this timezone:
 *
 * <pre>
 *  @Scheduled(cron = "0 15 10 15 * ?", zone = "Europe/Paris")
 * </pre>
 * <p>
 * With this configuration, Spring will schedule the annotated method to run at 10:15 AM on the 15th day of every month
 * in Paris time.
 *
 * @author Rohtash Lakra
 * @created 12/5/22 22:32 PM
 */
public class TaskUsingCronExpression {

    @Scheduled(cron = "0 15 10 15 * ?")
    public void scheduleTaskUsingCronExpression() {
        long now = System.currentTimeMillis() / 1000;
        System.out.println("schedule tasks using cron jobs - " + now);
    }
}
