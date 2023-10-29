package com.rslakra.services.automobile.config;

import com.rslakra.services.automobile.service.security.interceptor.LoggerInterceptor;
import com.rslakra.services.automobile.service.security.interceptor.SessionTimerInterceptor;
import com.rslakra.services.automobile.service.security.interceptor.UserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public WebMvcConfig() {
        super();
    }

    /**
     * API
     *
     * @param registry
     */
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
//        registry.addViewController("/anonymous.html");
//
//        registry.addViewController("/login.html");
//        registry.addViewController("/homepage.html");
//        registry.addViewController("/console.html");
//        registry.addViewController("/csrfHome.html");
    }

//    /**
//     * ViewResolver
//     *
//     * @return
//     */
//    @Bean
//    public ViewResolver viewResolver() {
//        final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setViewClass(JstlView.class);
//        viewResolver.setPrefix("/WEB-INF/views/jsp/");
//        viewResolver.setSuffix(".jsp");
//        return viewResolver;
//    }

    /**
     * JSP ViewResolver
     *
     * @return
     */
    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor());
        registry.addInterceptor(new UserInterceptor());
        registry.addInterceptor(new SessionTimerInterceptor());
    }
}