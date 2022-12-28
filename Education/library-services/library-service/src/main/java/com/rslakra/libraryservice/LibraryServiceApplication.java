package com.rslakra.libraryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 */
@SpringBootApplication
@EnableJpaRepositories
public class LibraryServiceApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(LibraryServiceApplication.class, args);
    }

}
