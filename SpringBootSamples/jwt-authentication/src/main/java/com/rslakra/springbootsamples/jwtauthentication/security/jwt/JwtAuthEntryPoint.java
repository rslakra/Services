package com.rslakra.springbootsamples.jwtauthentication.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthEntryPoint.class);

    /**
     * @param request
     * @param response
     * @param exception
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
        throws IOException, ServletException {
        LOGGER.debug("+commence({}, {}, {})", request, response, exception);
        LOGGER.error("Unauthorized error. Message - {}", exception.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error -> Unauthorized");
        LOGGER.debug("-commence(), response:{}", response);
    }
}
