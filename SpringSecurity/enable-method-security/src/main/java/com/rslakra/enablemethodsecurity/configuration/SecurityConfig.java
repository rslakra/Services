package com.rslakra.enablemethodsecurity.configuration;

import static org.springframework.beans.factory.config.BeanDefinition.ROLE_INFRASTRUCTURE;

import com.rslakra.enablemethodsecurity.authentication.UserDetailsServiceImpl;
import com.rslakra.enablemethodsecurity.authorization.AuthorizationManagerImpl;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.authorization.method.AuthorizationManagerBeforeMethodInterceptor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    /**
     * @param httpSecurity
     * @param userDetailsService
     * @param passwordEncoder
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity, UserDetailsService userDetailsService,
                                                       BCryptPasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManagerBuilder
            authManagerBuilder =
            httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        return authManagerBuilder.build();
    }

    /**
     * @param passwordEncoder
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder passwordEncoder) {
        return new UserDetailsServiceImpl(passwordEncoder);
    }

    /**
     * @return
     */
    @Bean
    public AuthorizationManager<MethodInvocation> authorizationManager() {
        return new AuthorizationManagerImpl<>();
    }

    /**
     * @param authorizationManager
     * @return
     */
    @Bean
    @Role(ROLE_INFRASTRUCTURE)
    public Advisor authorizationManagerBeforeMethodInterception(
        AuthorizationManager<MethodInvocation> authorizationManager) {
        JdkRegexpMethodPointcut pattern = new JdkRegexpMethodPointcut();
        pattern.setPattern("com.rslakra.enablemethodsecurity.services.*");
        return new AuthorizationManagerBeforeMethodInterceptor(pattern, authorizationManager);
    }

    /**
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
            .disable()
            .authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    /**
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
