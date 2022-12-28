package com.rslakra.jwtauthentication7.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils implements Serializable {

    private static final long serialVersionUID = 8014003242969213108L;
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer";

    @Value("${jwt.secret}")
    private String secret;

    /**
     * Generates the JWT token.
     * <p>
     * While creating the token -
     * <pre>
     *      1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID.
     *      2. Sign the JWT using the HS512 algorithm and secret key.
     *      3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
     *          compaction of the JWT to a URL-safe string
     * </pre>
     *
     * @param claims
     * @param subject
     * @return
     */
    public String generateToken(final Map<String, Object> claims, final String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
            .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //generate token for user
    public String generateToken(final UserDetails userDetails) {
        final Map<String, Object> claims = new HashMap<>();
        return generateToken(claims, userDetails.getUsername());
    }

    /**
     * Returns the <code>Claims</code> extracted from the token.
     * <p>
     * To retrieve any information from token we will need the secret key.
     *
     * @param token
     * @return
     */
    public Claims extractAllClaimsFromToken(final String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * Returns the <code>Claims</code> extracted from the token.
     *
     * @param token
     * @param claimsResolver
     * @param <T>
     * @return
     */
    public <T> T extractClaimFromToken(final String token, final Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Returns the username extracted from the JWT token.
     *
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token) {
        return extractClaimFromToken(token, Claims::getSubject);
    }

    /**
     * Returns the <code>expiration date</code> extracted from the JWT token.
     *
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        return extractClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * Returns true if the token is valid otherwise false.
     *
     * @param token
     * @param userDetails
     * @return
     */
    public boolean isValidToken(final String token, final UserDetails userDetails) {
        final String username = getUserNameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Return true if the token is already expired otherwise false.
     *
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
}
