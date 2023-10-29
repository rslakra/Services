package com.rslakra.healthcare.routinecheckup.service.impl.security;

import com.rslakra.healthcare.routinecheckup.service.security.TokenComponent;
import com.rslakra.healthcare.routinecheckup.utils.components.holder.JwtConstants;
import com.rslakra.healthcare.routinecheckup.utils.components.holder.WebConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:22 PM
 */
@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler
    implements AuthenticationSuccessHandler {

    private final TokenComponent tokenComponent;

    private final JwtConstants jwtConstants;

    private final WebConstants webConstants;

    private final Integer MILLIS_IN_SECOND = 1000;

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication
    ) throws IOException, ServletException {
        UserDetails principal = (UserDetails) authentication.getPrincipal();

        long maxAge = jwtConstants.getExpirationTimeMs().intValue()
                      / MILLIS_IN_SECOND;
        String token = tokenComponent.generateToken(principal);
        ResponseCookie cookie = ResponseCookie
            .from(jwtConstants.getParameterName(), token)
            .sameSite(webConstants.getSameSite())
            .maxAge(maxAge)
            .secure(true)
            .httpOnly(true)
            .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        String url = "https://" + webConstants.getDomainName()
                     + ":" + webConstants.getAppPort()
                     + "/" + webConstants.getBasePath();
        response.addHeader(HttpHeaders.LOCATION, url);
        response.setStatus(HttpStatus.PERMANENT_REDIRECT.value());
    }

}
