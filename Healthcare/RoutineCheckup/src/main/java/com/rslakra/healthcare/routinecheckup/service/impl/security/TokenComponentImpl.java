package com.rslakra.healthcare.routinecheckup.service.impl.security;

import com.rslakra.healthcare.routinecheckup.entity.UserEntity;
import com.rslakra.healthcare.routinecheckup.service.security.TokenComponent;
import com.rslakra.healthcare.routinecheckup.utils.components.holder.JwtConstants;
import com.rslakra.healthcare.routinecheckup.utils.components.holder.Messages;
import com.rslakra.healthcare.routinecheckup.utils.components.holder.RegistrationConstants;
import com.rslakra.healthcare.routinecheckup.utils.exceptions.JwtTokenExpiredException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:23 PM
 */
@Component
@RequiredArgsConstructor
public class TokenComponentImpl implements TokenComponent {

    private final JwtConstants jwtConstants;

    private final RegistrationConstants registrationConstants;

    private final Messages messages;

    private final Integer MILLIS_IN_SECOND = 1000;

    @Override
    public String generateRegistrationToken(UserEntity userEntity) {
        String result = generateToken(
            userEntity.getLogin(),
            registrationConstants.getRegistrationTimeMs(),
            registrationConstants.getRegistrationTokenKey(),
            jwtConstants.getLoginFieldName()
        );
        return result;
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        String result = generateToken(
            userDetails.getUsername(),
            jwtConstants.getExpirationTimeMs(),
            jwtConstants.getJwtKey(),
            jwtConstants.getLoginFieldName()
        );
        return result;
    }

    @Override
    public DefaultClaims parse(String token) {
        DefaultClaims body = (DefaultClaims) Jwts.parser()
            .setSigningKey(jwtConstants.getJwtKey())
            .parse(token)
            .getBody();
        validateTokenClaims(body);

        return body;
    }

    @Override
    public DefaultClaims parseRegistrationToken(String token) {
        DefaultClaims body = (DefaultClaims) Jwts.parser()
            .setSigningKey(
                registrationConstants.getRegistrationTokenKey()
            )
            .parse(token)
            .getBody();
        validateTokenClaims(body);

        return body;
    }

    @Override
    public String getLoginFromToken(String token) {
        DefaultClaims claims = parse(token);
        String login = getLoginFromClaims(claims);
        return login;
    }

    @Override
    public String getLoginFromRegistrationToken(String token) {
        DefaultClaims claims = parseRegistrationToken(token);
        String login = getLoginFromClaims(claims);
        return login;
    }

    @Override
    public Optional<String> getTokenFromRequest(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(
            request,
            jwtConstants.getParameterName()
        );
        Optional<String> token
            = Optional.ofNullable(cookie).map(Cookie::getValue);

        return token;
    }

    private String getLoginFromClaims(DefaultClaims claims) {
        String login = claims.get(
            jwtConstants.getLoginFieldName(),
            String.class
        );
        return login;
    }

    private void validateTokenClaims(DefaultClaims claims) {
        Date expirationDate = claims.get(Claims.EXPIRATION, Date.class);
        Date currentDate = new Date();
        if (currentDate.after(expirationDate)) {
            throw new JwtTokenExpiredException(messages.getTokenExpired());
        }
    }

    private String generateToken(
        String login,
        Long tokenLifeMs,
        String tokenKey,
        String loginFieldName
    ) {
        Map<String, Object> claims = getBaseClaims(login, loginFieldName);

        Long issuedAtSeconds = (Long) claims.get(Claims.ISSUED_AT);
        Long issuedAtMs = issuedAtSeconds == null ?
                          (new Date()).getTime()
                                                  : issuedAtSeconds * MILLIS_IN_SECOND;
        long expirationAtMs = issuedAtMs + tokenLifeMs;

        String result = Jwts.builder()
            .setClaims(claims)
            .setExpiration(new Date(expirationAtMs))
            .signWith(SignatureAlgorithm.HS512, tokenKey)
            .compact();
        return result;
    }

    private Map<String, Object> getBaseClaims(
        String username,
        String loginFieldName
    ) {
        Map<String, Object> claims = new HashMap<>();

        Date issuedAt = new Date();
        long issuedAtSeconds = issuedAt.getTime() / MILLIS_IN_SECOND;
        claims.put(Claims.ISSUED_AT, issuedAtSeconds);
        claims.put(Claims.NOT_BEFORE, issuedAtSeconds);

        claims.put(loginFieldName, username);

        return claims;
    }

}
