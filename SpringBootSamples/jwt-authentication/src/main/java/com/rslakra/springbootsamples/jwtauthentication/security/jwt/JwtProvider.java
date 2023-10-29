package com.rslakra.springbootsamples.jwtauthentication.security.jwt;

import com.rslakra.springbootsamples.jwtauthentication.security.services.UserPrinciple;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *
 */
@Component
public class JwtProvider {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwtSecret}")
    private String jwtSecret;

    @Value("${jwtExpiration}")
    private int jwtExpiration;

    /**
     * @param jwtExpirationInMinutes
     * @return
     */
    public static Long getExpiryTime(int jwtExpirationInMinutes) {
        return (new Date().getTime() + jwtExpirationInMinutes * 10000);
    }

    /**
     * @param authentication
     * @return
     */
    public String createJWTToken(final Authentication authentication) {
        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();

        return Jwts.builder()
            .setSubject((userPrincipal.getUsername()))
            .setIssuedAt(new Date())
            .setExpiration(new Date(getExpiryTime(jwtExpiration)))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }

    /**
     * @param authToken
     * @return
     */
    public boolean validateJwtToken(final String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            LOGGER.error("Invalid JWT signature -> Message: {} ", ex);
        } catch (MalformedJwtException ex) {
            LOGGER.error("Invalid JWT token -> Message: {}", ex);
        } catch (ExpiredJwtException ex) {
            LOGGER.error("Expired JWT token -> Message: {}", ex);
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("Unsupported JWT token -> Message: {}", ex);
        } catch (IllegalArgumentException ex) {
            LOGGER.error("JWT claims string is empty -> Message: {}", ex);
        }

        return false;
    }

    /**
     * @param jwtToken
     * @return
     */
    public String getPrincipleFromJwtToken(final String jwtToken) {
        return Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(jwtToken)
            .getBody().getSubject();
    }
}
