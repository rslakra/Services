package com.rslakra.alertservice.jobs;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * The fixedRate property runs the scheduled task at every n millisecond. It doesn't check for any previous executions
 * of the task.
 * <p>
 * This is useful when all executions of the task are independent. If we don't expect to exceed the size of the memory
 * and the thread pool, fixedRate should be quite handy.
 * <p>
 * Although, if the incoming tasks do not finish quickly, it's possible they end up with “Out of Memory exception”.
 *
 * @author Rohtash Lakra
 * @created 12/5/22 22:29 PM
 */
@EnableAsync
public class FixedRateAsyncTask {

    @Async
    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTaskAsync() throws InterruptedException {
        System.out.println("Fixed rate task async - " + System.currentTimeMillis() / 1000);
        Thread.sleep(2000);
    }
}
