package com.rslakra.services.automobile.config;

import com.devamatre.framework.spring.context.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author Rohtash Lakra
 * @created 8/4/21 5:52 PM
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditJpaConfig {

    /**
     * @return
     */
    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }
}
