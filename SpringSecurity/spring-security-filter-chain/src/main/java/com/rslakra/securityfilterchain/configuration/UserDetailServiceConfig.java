package com.rslakra.securityfilterchain.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserDetailServiceConfig {

    /**
     *
     * @param passwordEncoder
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(User.withUsername("user")
          .password(passwordEncoder.encode("userPass"))
          .roles("USER")
          .build());

        userDetailsManager.createUser(User.withUsername("admin")
          .password(passwordEncoder.encode("adminPass"))
          .roles("ADMIN", "USER")
          .build());

        return userDetailsManager;
    }

    /**
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
