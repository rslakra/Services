package com.rslakra.jwtauthentication.config;

import com.rslakra.jwtauthentication.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @author Rohtash Lakra
 * @created 5/19/20 10:57 AM
 */
@Configuration
@EnableJpaAuditing
public class JpaConfig {

    /**
     * @return
     */
    @Bean
    public AuditorAware<User> auditor() {
        return () -> Optional.ofNullable(SecurityContextHolder.getContext())
            .map(SecurityContext::getAuthentication)
            .filter(Authentication::isAuthenticated)
            .map(Authentication::getPrincipal)
            .map(User.class::cast);
    }
}
