package com.rslakra.services.automobile.service.security.config;

import com.rslakra.services.automobile.service.security.PasswordAuthenticationFilter;
import com.rslakra.services.automobile.service.security.UserDetailsAuthenticationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class HttpSecurityConfigurer extends AbstractHttpConfigurer<HttpSecurityConfigurer, HttpSecurity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpSecurityConfigurer.class);

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;

    public HttpSecurityConfigurer() {
        LOGGER.debug("HttpSecurityConfigurer()");
    }

    /**
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        LOGGER.debug("+configure({})", http);
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        http.addFilterBefore(authenticationFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class);
        LOGGER.debug("-configure()");
    }

    /**
     * @return
     */
    public static HttpSecurityConfigurer httpSecurityConfigurer() {
        return new HttpSecurityConfigurer();
    }

    /**
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        LOGGER.debug("filterChain({})", http);
        /* Allow frames only with the same origin, which is much more safe. */
        http
            .headers().frameOptions().disable()
            .and()
            /* Ignore only h2-console csrf, spring-security 4. */
            .csrf().ignoringAntMatchers("/h2/**")
            .and()
            /* Authorization */
            .authorizeRequests()
            /* Testing API - remove it in production */
            .antMatchers("/auth/**", "/rest/**")
            .permitAll()
            .antMatchers("/h2/**")
            .permitAll()
            .antMatchers("/css/**", "/js/**", "/*.html", "/resources/**")
            .permitAll()
            .and()
            .formLogin()
            .loginPage("/login")
            .and()
            .logout()
            .logoutUrl("/logout")
            .and()
            .apply(httpSecurityConfigurer());

        return http.build();
    }

    /**
     * @param authenticationManager
     * @return
     * @throws Exception
     */
    public PasswordAuthenticationFilter authenticationFilter(AuthenticationManager authenticationManager) {
        LOGGER.debug("+authenticationFilter({})", authenticationManager);
        PasswordAuthenticationFilter passwordAuthFilter = new PasswordAuthenticationFilter(passwordEncoder);
        passwordAuthFilter.setAuthenticationManager(authenticationManager);
        passwordAuthFilter.setAuthenticationFailureHandler(failureHandler());
        LOGGER.debug("-authenticationFilter(), passwordAuthFilter: {}", passwordAuthFilter);
        return passwordAuthFilter;
    }

    /**
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        LOGGER.debug("configureGlobal({})", auth);
        auth.authenticationProvider(authProvider());
    }

    /**
     * @return
     */
    public AuthenticationProvider authProvider() {
        LOGGER.debug("authProvider()");
        AuthenticationProvider
            authProvider =
            new UserDetailsAuthenticationProvider(passwordEncoder, userDetailsService);

        return authProvider;
    }

    /**
     * @return
     */
    public SimpleUrlAuthenticationFailureHandler failureHandler() {
        LOGGER.debug("failureHandler()");
        return new SimpleUrlAuthenticationFailureHandler("/login?error=true");
    }

}
