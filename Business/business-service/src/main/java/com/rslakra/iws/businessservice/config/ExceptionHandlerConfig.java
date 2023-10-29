//package com.rslakra.iws.taskservice.config;
//
//import org.springframework.boot.web.error.ErrorAttributeOptions;
//import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
//import org.springframework.boot.web.servlet.error.ErrorAttributes;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.context.request.WebRequest;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
///**
// * @author Rohtash Lakra
// * @created 3/21/23 4:45 PM
// */
//@Configuration
//public class ExceptionHandlerConfig {
//
//    private static final String DEFAULT_KEY_TIMESTAMP = "timestamp";
//    private static final String DEFAULT_KEY_STATUS = "status";
//    private static final String DEFAULT_KEY_ERROR = "error";
//    private static final String DEFAULT_KEY_ERRORS = "errors";
//    private static final String DEFAULT_KEY_MESSAGE = "message";
//    //private static final String DEFAULT_KEY_PATH = "path";
//
//    public static final String KEY_STATUS = "status";
//    public static final String KEY_ERROR = "error";
//    public static final String KEY_MESSAGE = "message";
//    public static final String KEY_TIMESTAMP = "timestamp";
//    public static final String KEY_ERRORS = "errors";
//
//    //
//
//    @Bean
//    public ErrorAttributes errorAttributes() {
//        return new DefaultErrorAttributes() {
//
//            @Override
//            public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions errorAttributeOptions) {
//                Map<String, Object> defaultMap = super.getErrorAttributes(webRequest, errorAttributeOptions);
//                Map<String, Object> errorAttributes = new LinkedHashMap<>();
//                // Customize.
//                // For eg: Only add the keys you want.
//                errorAttributes.put(KEY_STATUS, defaultMap.get(DEFAULT_KEY_STATUS));
//                errorAttributes.put(KEY_MESSAGE, defaultMap.get(DEFAULT_KEY_MESSAGE));
//
//                return errorAttributes;
//            }
//        };
//    }
//}
