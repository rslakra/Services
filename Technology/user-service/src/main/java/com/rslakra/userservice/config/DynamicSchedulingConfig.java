package com.rslakra.userservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.Task;

/**
 * @author Rohtash Lakra
 * @created 12/5/22 22:35 PM
 */
@Configuration
@EnableScheduling
public class DynamicSchedulingConfig implements SchedulingConfigurer {

//    @Autowired
//    private TickService tickService;
//
//    @Bean
//    public Executor taskExecutor() {
//        return Executors.newSingleThreadScheduledExecutor();
//    }
//
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        taskRegistrar.setScheduler(taskExecutor());
//        taskRegistrar.addTriggerTask(
//            new Runnable() {
//                @Override
//                public void run() {
//                    tickService.tick();
//                }
//            },
//            new Trigger() {
//                @Override
//                public Date nextExecutionTime(TriggerContext context) {
//                    Optional<Date> lastCompletionTime =
//                        Optional.ofNullable(context.lastCompletionTime());
//                    Instant nextExecutionTime =
//                        lastCompletionTime.orElseGet(Date::new).toInstant()
//                            .plusMillis(tickService.getDelay());
//                    return Date.from(nextExecutionTime);
//                }
//            }
//        );
//    }


    /**
     * Callback allowing a {@link TaskScheduler TaskScheduler} and specific {@link Task Task} instances to be registered
     * against the given the {@link ScheduledTaskRegistrar}.
     *
     * @param taskRegistrar the registrar to be configured.
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        
    }
}
