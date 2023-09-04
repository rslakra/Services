package com.rslakra.jwtauthentication5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ServletComponentScan
@SpringBootApplication
//@EnableJpaRepositories("com.rslakra.jwtauthentication5")
//@EntityScan("com.rslakra.jwtauthentication5.model")
//@SpringBootApplication
public class JwtAuthentication5Application {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(JwtAuthentication5Application.class, args);
    }

}
