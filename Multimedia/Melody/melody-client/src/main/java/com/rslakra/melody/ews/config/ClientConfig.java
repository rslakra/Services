package com.rslakra.melody.ews.config;

import com.devamatre.framework.spring.client.ApiRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Rohtash Lakra
 * @created 7/11/23 8:19 AM
 */
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class ClientConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientConfig.class);

    @Value("${apiHostBaseUrl}")
    private String apiHostBaseUrl;

    @Bean
    public ApiRestClient apiRestClient() {
        LOGGER.debug("apiRestClient(), apiHostBaseUrl:{}", apiHostBaseUrl);
        return new ApiRestClient(apiHostBaseUrl);
    }

}
