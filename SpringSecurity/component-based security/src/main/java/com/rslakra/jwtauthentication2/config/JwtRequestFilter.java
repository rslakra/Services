package com.rslakra.jwtauthentication2.config;

import com.rslakra.jwtauthentication2.service.JwtUserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private static Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

    @Autowired
    private JwtUtils jwtTokenUtil;

    @Autowired
    private JwtUserService jwtUserDetailsService;

    /**
     * @param request
     * @param response
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException {
        logger.debug("+doFilterInternal(" + request + ", " + response + ", " + chain + ")");
        final String requestToken = request.getHeader(JwtUtils.AUTHORIZATION);
        String userName = null;
        String jwtToken = null;
        /**
         * JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
         */
        if (requestToken != null && requestToken.startsWith(JwtUtils.BEARER)) {
            jwtToken = requestToken.substring(7);
            try {
                userName = jwtTokenUtil.getUserNameFromToken(jwtToken);
            } catch (IllegalArgumentException ex) {
                logger.error("Unable to get JWT Token", ex);
            } catch (ExpiredJwtException ex) {
                logger.error("JWT Token has expired", ex);
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }

        // Once we get the token validate it.
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            final UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(userName);
            /*
             * if token is valid configure Spring Security to manually set authentication
             */
            if (jwtTokenUtil.isValidToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                /**
                 * After setting the Authentication in the context,
                 * we specify that the current user is authenticated.
                 * So it passes the Spring Security Configurations successfully.
                 */
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        chain.doFilter(request, response);
        logger.debug("-doFilterInternal()");
    }

}
