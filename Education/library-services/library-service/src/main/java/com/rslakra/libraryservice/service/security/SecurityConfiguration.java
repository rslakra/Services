//package com.rslakra.libraryservice.service.security;
//
//import com.rslakra.libraryservice.service.UserDetailsServiceImpl;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//
///**
// * @author Rohtash Lakra
// * @created 8/20/21 5:08 PM
// */
//@Configuration
//@EnableWebSecurity
//@Profile("!https")
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    /**
//     * @return
//     */
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new UserDetailsServiceImpl();
//    }
//
//    /**
//     * @return
//     */
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    /**
//     * @return
//     */
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService());
//        authProvider.setPasswordEncoder(passwordEncoder());
//
//        return authProvider;
//    }
//
//    /**
//     * @param auth
//     * @throws Exception
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }
//
//    /**
//     * <pre>
//     * loginPage() – the custom login page
//     * loginProcessingUrl() – the URL to submit the username and password to
//     * defaultSuccessUrl() – the landing page after a successful login
//     * failureUrl() – the landing page after an unsuccessful login
//     * logoutUrl() – the custom logout
//     * </pre>
//     *
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
////            .antMatchers("/").hasAnyAuthority("USER", "CREATOR", "EDITOR", "ADMIN")
////            .antMatchers("/new").hasAnyAuthority("ADMIN", "CREATOR")
////            .antMatchers("/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
////            .antMatchers("/delete/**").hasAuthority("ADMIN")
//            .anyRequest().authenticated()
//            .and()
//            .formLogin().permitAll()
//            .and()
//            .logout().permitAll()
//            .and()
//            .exceptionHandling().accessDeniedPage("/403")
//        ;
//
////        http
////            .csrf().disable()
////            .authorizeRequests()
////            .antMatchers("/resources/**").permitAll()
////            .antMatchers("/admin/**").hasRole("ADMIN")
////            .antMatchers("/login*").permitAll()
////            .anyRequest().authenticated()
////            .and()
////            .formLogin()
////            .loginPage("/login")
////            .permitAll()
//////            .loginProcessingUrl("/login")
//////            .defaultSuccessUrl("/index.html", true)
//////            .failureUrl("/login.html?error=true")
//////            .failureHandler(authenticationFailureHandler())
////            .and()
////            .logout()
////            .permitAll()
//////            .logoutUrl("/logout")
//////            .deleteCookies("JSESSIONID")
////            .logoutSuccessHandler(logoutSuccessHandler());
//    }
//
//    /**
//     * @param auth
//     * @throws Exception
//     */
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication()
////            .withUser("user")
////            .password(passwordEncoder().encode("password"))
////            .roles("USER");
////        super.configure(auth);
////    }
//
////    /**
////     * @return
////     */
////    @Bean
////    public LogoutSuccessHandler logoutSuccessHandler() {
////        return new LogoutSuccessHandlerImpl();
////    }
////
////    /**
////     * @return
////     */
////    @Bean
////    public AccessDeniedHandler accessDeniedHandler() {
////        return new AccessDeniedHandlerImpl();
////    }
////
////    /**
////     * @return
////     */
////    @Bean
////    public AuthenticationFailureHandler authenticationFailureHandler() {
////        return new AuthenticationFailureHandlerImpl();
////    }
//}
