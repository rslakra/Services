package com.rslakra.liquibaseservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PreDestroy;

/**
 * @author Rohtash Lakra
 * @created 10/20/22 3:01 PM
 */
public class OnAppShutdown {

    // LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(OnAppShutdown.class);

    /**
     * @throws Exception
     */
    @PreDestroy
    public void onDestroy() {
        LOGGER.debug("Spring Container is destroyed!");
//        Thread.dumpStack();
    }
}

