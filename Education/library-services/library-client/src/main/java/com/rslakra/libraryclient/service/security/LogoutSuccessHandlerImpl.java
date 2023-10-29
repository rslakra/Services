//package com.rslakra.libraryclient.service.security;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @author Rohtash Lakra
// * @created 8/20/21 5:41 PM
// */
//public class LogoutSuccessHandlerImpl extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {
//
//    public LogoutSuccessHandlerImpl() {
//        super();
//    }
//
//    /**
//     * @param servletRequest
//     * @param servletResponse
//     * @param authentication
//     * @throws IOException
//     * @throws ServletException
//     */
//    @Override
//    public void onLogoutSuccess(final HttpServletRequest servletRequest, final HttpServletResponse servletResponse,
//                                final Authentication authentication) throws
//                                                                     IOException,
//                                                                     ServletException {
//        final String refererUrl = servletRequest.getHeader("Referer");
//        System.out.println(refererUrl);
//
//        super.onLogoutSuccess(servletRequest, servletResponse, authentication);
//    }
//
//}
