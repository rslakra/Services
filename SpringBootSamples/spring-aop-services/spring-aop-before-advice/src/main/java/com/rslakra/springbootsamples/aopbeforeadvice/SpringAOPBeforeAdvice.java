package com.rslakra.springbootsamples.aopbeforeadvice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAOPBeforeAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringAOPBeforeAdvice.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        LOGGER.debug("+main({})", args);
        SpringApplication.run(SpringAOPBeforeAdvice.class, args);
        LOGGER.debug("-main()");
    }

}
