package com.rslakra.jwtauthentication.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    /**
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        logger.debug("Jwt authentication failed:" + authException);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Jwt authentication failed");

    }

}
