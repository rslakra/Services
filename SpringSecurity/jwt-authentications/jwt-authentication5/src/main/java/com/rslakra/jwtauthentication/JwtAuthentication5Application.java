package com.rslakra.jwtauthentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ServletComponentScan
@SpringBootApplication(scanBasePackages = "com.rslakra.jwtauthentication")
//@EnableJpaRepositories("com.rslakra.jwtauthentication")
//@EntityScan("com.rslakra.jwtauthentication.model")
//@SpringBootApplication
public class JwtAuthentication5Application {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
    }

}
