package com.rslakra.jwtauthentication.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private static Logger logger = LoggerFactory.getLogger(JwtSecurityConfigurer.class);

    private JwtTokenProvider jwtTokenProvider;

    /**
     * @param jwtTokenProvider
     */
    public JwtSecurityConfigurer(JwtTokenProvider jwtTokenProvider) {
        logger.debug("JwtSecurityConfigurer({})", jwtTokenProvider);
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        logger.debug("configure({})", http);
        JwtTokenAuthenticationFilter customFilter = new JwtTokenAuthenticationFilter(jwtTokenProvider);
        http.exceptionHandling()
            .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
            .and()
            .addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
