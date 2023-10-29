package com.rslakra.springbootsamples.emailservice.config.security;

import com.rslakra.springbootsamples.emailservice.service.AuthUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 3:28 PM
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringSecurityConfig.class);

    @Autowired
    private AuthUserDetailsService userDetailsService;

    private static final String[] PUBLIC_MATCHERS = {
        "/webjars/**",
        "/css/**",
        "/js/**",
        "/images/**",
        "/",
        "/about/**",
        "/contact/**",
        "/error/**/*",
        "/console/**",
        "/signup",
        "/signup/user",
        "/forgotPassword**",
        "/reset-password**"
    };

    /**
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
            .authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login").defaultSuccessUrl("/home")
            .failureUrl("/login?error")
            .permitAll()
            .and()
            .logout()
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login?logout")
            .permitAll()
            .and()
            .exceptionHandling().accessDeniedPage("/403");
    }

    /**
     * @return
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        LOGGER.debug("authenticationProvider called for authorization");
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());

        return authenticationProvider;
    }

    /**
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    /**
     * @return
     * @throws Exception
     */
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }
}
