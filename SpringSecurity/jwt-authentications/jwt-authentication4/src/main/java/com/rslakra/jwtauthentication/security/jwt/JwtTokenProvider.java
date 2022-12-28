package com.rslakra.jwtauthentication.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@Component
public class JwtTokenProvider {

    private static Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer";

    @Autowired
    JwtProperties jwtProperties;

    @Autowired
    private UserDetailsService userDetailsService;

    private String secretKey;

    @PostConstruct
    protected void init() {
        logger.debug("init()");
        secretKey = Base64.getEncoder().encodeToString(jwtProperties.getSecretKey().getBytes());
    }

    /**
     * @param username
     * @param roles
     * @return
     */
    public String createToken(String username, List<String> roles) {
        logger.debug("createToken({}, {})", username, roles);
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);
        Date now = new Date();
        Date validity = new Date(now.getTime() + jwtProperties.getValidityInMillis());
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
    }

    /**
     * @param token
     * @return
     */
    public Authentication getAuthentication(final String token) {
        logger.debug("Authentication({})", token);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    /**
     * @param token
     * @return
     */
    public String getUsername(final String token) {
        logger.debug("getUsername({})", token);
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * @param request
     * @return
     */
    public String resolveToken(HttpServletRequest request) {
        logger.debug("resolveToken({})", request);
        final String accessToken = request.getHeader(AUTHORIZATION);
        if (accessToken != null && accessToken.startsWith(BEARER)) {
            return accessToken.substring(7, accessToken.length());
        }

        return null;
    }

    /**
     * @param token
     * @return
     */
    public boolean hasValidToken(final String token) {
        logger.debug("+hasValidToken({})", token);
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            if (claims.getBody().getExpiration().before(new Date())) {
                logger.debug("-hasValidToken(), result:false");
                return false;
            }

            logger.debug("-hasValidToken(), result:true");
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            logger.debug("-hasValidToken(), result:false");
            throw new InvalidJwtAuthenticationException("Invalid JWT Token!", ex);
        }
    }

}
