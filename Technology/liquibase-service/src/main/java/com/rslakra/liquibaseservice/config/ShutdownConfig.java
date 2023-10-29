package com.rslakra.liquibaseservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Rohtash Lakra
 * @created 10/20/22 3:02 PM
 */
@Configuration
public class ShutdownConfig {

    /**
     * @return
     */
    @Bean
    public OnAppShutdown getTerminateBean() {
        return new OnAppShutdown();
    }
}
