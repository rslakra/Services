package com.rslaka.springboot.oauthclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * However, if we need to deploy in a web container, we need to extend SpringBootServletInitializer.
 * <p>
 * This binds our application's Servlet, Filter and ServletContextInitializer to the runtime server, which is necessary
 * for our application to run.
 */
@SpringBootApplication
public class SpringBootOAuthClientApplication extends SpringBootServletInitializer {

    /**
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringBootOAuthClientApplication.class);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringBootOAuthClientApplication.class, args);
    }
}
