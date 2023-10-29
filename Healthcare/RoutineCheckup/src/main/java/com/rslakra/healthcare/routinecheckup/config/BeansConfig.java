package com.rslakra.healthcare.routinecheckup.config;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 3:55 PM
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeansConfig {

    @Bean
    public RestOperations restOperations() {
        RestOperations bean = new RestTemplate();
        return bean;
    }

}
