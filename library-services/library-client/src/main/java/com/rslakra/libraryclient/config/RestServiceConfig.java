package com.rslakra.libraryclient.config;

import com.rslakra.libraryclient.service.RestService;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.util.DefaultUriBuilderFactory;

/**
 * @author Rohtash Lakra
 * @created 10/9/21 4:52 PM
 */
@Configuration
@PropertySource("classpath:application.properties")
public class RestServiceConfig {

    @Autowired
    private CloseableHttpClient httpClient;

    @Value("${apiHostBaseUrl}")
    private String apiHostBaseUrl;

    /**
     * @return
     */
    @Bean
    public RestService restService() {
        final RestService restService = new RestService(apiHostBaseUrl, clientHttpRequestFactory());
        restService.setUriTemplateHandler(new DefaultUriBuilderFactory(apiHostBaseUrl));
        return restService;
    }

    /**
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setHttpClient(httpClient);
        return clientHttpRequestFactory;
    }
}
