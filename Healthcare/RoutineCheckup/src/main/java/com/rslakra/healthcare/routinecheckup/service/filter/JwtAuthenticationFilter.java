package com.rslakra.healthcare.routinecheckup.service.filter;

import com.rslakra.healthcare.routinecheckup.dto.security.JwtTokenAuthentication;
import com.rslakra.healthcare.routinecheckup.service.security.TokenComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:17 PM
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    private final TokenComponent tokenComponent;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        Optional<String> tokenOpt
            = tokenComponent.getTokenFromRequest(request);
        if (!tokenOpt.isPresent()) {
            SecurityContextHolder.getContext().setAuthentication(null);
            filterChain.doFilter(request, response);
            return;
        }
        String token = tokenOpt.get();

        String login = tokenComponent.getLoginFromToken(token);
        UserDetails userDetails
            = userDetailsService.loadUserByUsername(login);
        Authentication result = new JwtTokenAuthentication(userDetails, token);
        result.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(result);

        filterChain.doFilter(request, response);
    }

}
