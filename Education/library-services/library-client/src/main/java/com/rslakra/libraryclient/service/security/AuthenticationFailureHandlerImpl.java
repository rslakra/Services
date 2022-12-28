//package com.rslakra.libraryclient.service.security;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//
//import java.io.IOException;
//import java.util.Calendar;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @author Rohtash Lakra
// * @created 8/20/21 5:39 PM
// */
//public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
//
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
//                                        AuthenticationException e) throws IOException, ServletException {
//        servletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
//        String jsonPayload = "{\"message\" : \"%s\", \"timestamp\" : \"%s\" }";
//        servletResponse.getOutputStream()
//            .println(String.format(jsonPayload, e.getMessage(), Calendar.getInstance().getTime()));
//
//    }
//}
