package com.rslakra.jwtauthentication.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class JwtTokenAuthenticationFilter extends GenericFilterBean {

    private static Logger logger = LoggerFactory.getLogger(JwtTokenAuthenticationFilter.class);
    private JwtTokenProvider jwtTokenProvider;

    /**
     * @param jwtTokenProvider
     */
    public JwtTokenAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        logger.debug("JwtTokenAuthenticationFilter({})", jwtTokenProvider);
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * @param request
     * @param response
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
        throws IOException, ServletException {
        logger.debug("+doFilter({})", jwtTokenProvider);
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
        logger.debug("token:{}", token);
        if (token != null && jwtTokenProvider.hasValidToken(token)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            if (authentication != null) {
                logger.debug("User:{}", authentication.getName());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
        logger.debug("-doFilter()");
    }

}
