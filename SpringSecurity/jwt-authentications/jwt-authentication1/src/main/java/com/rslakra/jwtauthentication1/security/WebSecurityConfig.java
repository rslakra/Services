package com.rslakra.jwtauthentication1.security;

import com.rslakra.jwtauthentication1.security.jwt.AuthTokenFilter;
import com.rslakra.jwtauthentication1.security.jwt.JwtAuthenticationEntryPoint;
import com.rslakra.jwtauthentication1.security.services.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtAuthenticationEntryPoint authEntryPoint;

    /**
     * @param userDetailsService
     * @param authEntryPoint
     */
    public WebSecurityConfig(final UserDetailsServiceImpl userDetailsService,
                             final JwtAuthenticationEntryPoint authEntryPoint) {
        logger.debug("WebSecurityConfig({}, {})", userDetailsService, authEntryPoint);
        this.userDetailsService = userDetailsService;
        this.authEntryPoint = authEntryPoint;
    }

    @Bean
    public AuthTokenFilter authJwtTokenFilter() {
        logger.debug("authJwtTokenFilter()");
        return new AuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
        logger.debug("configure({})", authManagerBuilder);
        authManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        logger.debug("authenticationManagerBean()");
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        logger.debug("passwordEncoder()");
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.debug("configure({})", http);
        http.cors()
            .and().csrf().disable()
            .exceptionHandling().authenticationEntryPoint(authEntryPoint)
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().authorizeRequests().antMatchers("/auth/**").permitAll()
            .antMatchers("/api/home/**").permitAll()
            .anyRequest().authenticated();
        http.addFilterBefore(authJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
