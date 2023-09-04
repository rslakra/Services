package com.rslakra.springsecurity.javajwt.config;

import com.rslakra.springsecurity.javajwt.service.SecretsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.csrf.CsrfTokenRepository;

@Configuration
public class CSRFConfig {

    private final SecretsService secretsService;

    /**
     * @param secretsService
     */
    @Autowired
    public CSRFConfig(SecretsService secretsService) {
        this.secretsService = secretsService;
    }

    @Bean
    @ConditionalOnMissingBean
    public CsrfTokenRepository jwtCsrfTokenRepository() {
        return new JWTCsrfTokenRepository(secretsService.getHS256SecretBytes());
    }
}
