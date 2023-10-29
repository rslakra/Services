//package com.rslakra.libraryservice.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @author Rohtash Lakra
// * @created 8/20/21 5:45 PM
// */
//@EnableWebMvc
//@Configuration
//public class WebMvcConfiguration implements WebMvcConfigurer {
//
//    public WebMvcConfiguration() {
//        super();
//    }
//
//    /**
//     * @param registry
//     */
//    @Override
//    public void addViewControllers(final ViewControllerRegistry registry) {
////        registry.addViewController("/login").setViewName("login");
////        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        registry.addViewController("/403").setViewName("403");
//    }
//
////    /**
////     *
////     * @return
////     */
////    @Bean
////    public ViewResolver viewResolver() {
////        final InternalResourceViewResolver bean = new InternalResourceViewResolver();
////        bean.setViewClass(JstlView.class);
////        bean.setPrefix("/WEB-INF/view/");
////        bean.setSuffix(".jsp");
////
////        return bean;
////    }
//}
