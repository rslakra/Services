//package com.rslakra.libraryservice.config;
//
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author Rohtash Lakra
// * @created 8/27/21 4:24 PM
// */
//@Configuration
//public class SwaggerConfiguration {
//
//    public static final String AUTHORIZATION = "Authorization";
//
//    private ApiInfo apiInfo() {
//        return new ApiInfo("My REST API",
//                           "Some custom description of API.",
//                           "1.0",
//                           "Terms of service",
//                           new Contact("Sallo Szrajbman", "www.baeldung.com", "salloszraj@gmail.com"),
//                           "License of API",
//                           "API license URL",
//                           Collections.emptyList());
//    }
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//            .apiInfo(apiInfo())
//            .securityContexts(Arrays.asList(securityContext()))
//            .securitySchemes(Arrays.asList(apiKey()))
//            .select()
//            .apis(RequestHandlerSelectors.any())
//            .paths(PathSelectors.any())
//            .build();
//    }
//
//    private ApiKey apiKey() {
//        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//            .securityReferences(defaultAuth())
//            .build();
//    }
//
//    List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope
//            = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
//    }
//}
