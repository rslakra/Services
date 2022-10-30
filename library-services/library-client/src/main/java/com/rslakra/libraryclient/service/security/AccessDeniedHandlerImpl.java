//package com.rslakra.libraryclient.service.security;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.access.AccessDeniedHandler;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @author Rohtash Lakra
// * @created 8/20/21 5:36 PM
// */
//public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
//
//    // LOGGER
//    public static Logger LOGGER = LoggerFactory.getLogger(AccessDeniedHandlerImpl.class);
//
//    /**
//     * @param servletRequest
//     * @param servletResponse
//     * @param e
//     * @throws IOException
//     * @throws ServletException
//     */
//    @Override
//    public void handle(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
//                       org.springframework.security.access.AccessDeniedException e)
//        throws IOException, ServletException {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            LOGGER.warn("User: " + auth.getName() + " attempted to access the protected URL: "
//                        + servletRequest.getRequestURI());
//        }
//
//        servletResponse.sendRedirect(servletRequest.getContextPath() + "/accessDenied");
//
//    }
//}
