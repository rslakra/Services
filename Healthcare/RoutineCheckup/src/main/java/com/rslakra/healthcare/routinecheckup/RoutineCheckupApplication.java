package com.rslakra.healthcare.routinecheckup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.map.repository.config.EnableMapRepositories;

@PropertySource(value = {
    "classpath:messages.properties",
    "classpath:mail_messages.properties"
}, encoding = "UTF-8")
@EnableMapRepositories
@SpringBootApplication
public class RoutineCheckupApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(RoutineCheckupApplication.class, args);
    }

}
