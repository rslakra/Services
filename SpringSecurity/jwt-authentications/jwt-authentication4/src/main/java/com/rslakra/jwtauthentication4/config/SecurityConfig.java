package com.rslakra.jwtauthentication4.config;

import com.rslakra.jwtauthentication4.security.jwt.JwtSecurityConfigurer;
import com.rslakra.jwtauthentication4.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

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
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/auth/signin").permitAll()
            .antMatchers(HttpMethod.GET, "/vehicles/**").permitAll()
            .antMatchers(HttpMethod.DELETE, "/vehicles/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/v1/vehicles/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .apply(new JwtSecurityConfigurer(jwtTokenProvider));
        //@formatter:on
    }

}

