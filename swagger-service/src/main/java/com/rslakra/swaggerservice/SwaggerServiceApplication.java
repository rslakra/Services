package com.rslakra.swaggerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 */
@SpringBootApplication
@EnableJpaRepositories
public class SwaggerServiceApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SwaggerServiceApplication.class, args);
    }

}
