package com.rslakra.dockermicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class DockerMicroserviceApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(DockerMicroserviceApplication.class, args);
    }
}


