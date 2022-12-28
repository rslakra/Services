package com.rslakra.securityfilterchain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class SecurityFilterChainApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SecurityFilterChainApplication.class, args);
    }
}
