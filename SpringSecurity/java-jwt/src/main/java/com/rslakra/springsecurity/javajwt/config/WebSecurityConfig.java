package com.rslakra.springsecurity.javajwt.config;

import com.rslakra.springsecurity.javajwt.filter.JwtCsrfValidatorFilter;
import com.rslakra.springsecurity.javajwt.service.SecretsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CsrfTokenRepository jwtCsrfTokenRepository;

    @Autowired
    private SecretsService secretsService;

    // ordered so we can use binary search below
    private String[] ignoreCsrfAntMatchers = {
        "/dynamic-builder-compress", "/dynamic-builder-general", "/dynamic-builder-specific", "/set-secrets"
    };

    /**
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAfter(new JwtCsrfValidatorFilter(secretsService, ignoreCsrfAntMatchers), CsrfFilter.class)
            .csrf()
            .csrfTokenRepository(jwtCsrfTokenRepository)
            .ignoringAntMatchers(ignoreCsrfAntMatchers)
            .and()
            .authorizeRequests()
            .antMatchers("/**")
            .permitAll();
    }

}
