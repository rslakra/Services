package com.rslakra.springsecurity.javajwt.utils;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

import com.rslakra.springsecurity.javajwt.model.JwtResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SigningKeyResolver;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

public enum JWTUtils {
    INSTANCE;
    public static final String NEW_LINE = "\n";
    public static final String TAB = "\t";
    public static final String ISSUER = "iss";
    public static final String SUBJECT = "sub";
    public static final String AUDIENCE = "aud";
    public static final String EXPIRATION = "exp";
    public static final String NOT_BEFORE = "nbf";
    public static final String ISSUED_AT = "iat";
    public static final String JWT_ID = "jti";

    /**
     * @param servletRequest
     * @return
     */
    public static boolean isPort80(HttpServletRequest servletRequest) {
        return (Objects.nonNull(servletRequest) && servletRequest.getServerPort() == 80);
    }

    /**
     * @param servletRequest
     * @return
     */
    public static boolean isPort443(HttpServletRequest servletRequest) {
        return (Objects.nonNull(servletRequest) && servletRequest.getServerPort() == 443);
    }

    /**
     * @param servletRequest
     * @return
     */
    public static String getRequestUrl(HttpServletRequest servletRequest) {
        return servletRequest.getScheme()
               + "://" + servletRequest.getServerName()
               + ((isPort80(servletRequest) || isPort443(servletRequest)) ? "" : ":" + servletRequest.getServerPort());
    }


    /**
     * @param claims
     * @param secretBytes
     * @return
     */
    public static String jwtCompactBuilderWithClaims(final Map<String, Object> claims, final byte[] secretBytes) {
        final JwtBuilder jwtBuilder = Jwts.builder();
        claims.forEach((key, value) -> {
            switch (key) {
                case ISSUER:
                    INSTANCE.assertClaimType(key, value, String.class);
                    jwtBuilder.setIssuer((String) value);
                    break;
                case SUBJECT:
                    INSTANCE.assertClaimType(key, value, String.class);
                    jwtBuilder.setSubject((String) value);
                    break;
                case AUDIENCE:
                    INSTANCE.assertClaimType(key, value, String.class);
                    jwtBuilder.setAudience((String) value);
                    break;
                case EXPIRATION:
                    INSTANCE.assertClaimType(key, value, Long.class);
                    jwtBuilder.setExpiration(Date.from(Instant.ofEpochSecond(Long.parseLong(value.toString()))));
                    break;
                case NOT_BEFORE:
                    INSTANCE.assertClaimType(key, value, Long.class);
                    jwtBuilder.setNotBefore(Date.from(Instant.ofEpochSecond(Long.parseLong(value.toString()))));
                    break;
                case ISSUED_AT:
                    INSTANCE.assertClaimType(key, value, Long.class);
                    jwtBuilder.setIssuedAt(Date.from(Instant.ofEpochSecond(Long.parseLong(value.toString()))));
                    break;
                case JWT_ID:
                    INSTANCE.assertClaimType(key, value, String.class);
                    jwtBuilder.setId((String) value);
                    break;
                default:
                    jwtBuilder.claim(key, value);
            }
        });

        jwtBuilder.signWith(SignatureAlgorithm.HS256, secretBytes);
        return jwtBuilder.compact();
    }


    /**
     * @param claims
     * @param secretBytes
     * @return
     */
    public static JwtResponse jwtBuilderWithClaims(final Map<String, Object> claims, final byte[] secretBytes) {
        return new JwtResponse(jwtCompactBuilderWithClaims(claims, secretBytes));
    }

    /**
     * @param issuer
     * @param subject
     * @param issuedAtSeconds
     * @param expiryInSeconds
     * @param customClaims
     * @param secretBytes
     * @return
     */
    public static String jwtBuilder(String issuer, String subject, Long issuedAtSeconds, Long expiryInSeconds,
                                    Map<String, Object> customClaims, byte[] secretBytes) {
        final JwtBuilder jwtBuilder = Jwts.builder();
        if (Objects.nonNull(issuer)) {
            jwtBuilder.setIssuer(issuer);
        }

        if (Objects.nonNull(subject)) {
            jwtBuilder.setSubject(subject);
        }

        if (Objects.nonNull(customClaims)) {
            customClaims.forEach((key, value) -> jwtBuilder.claim(key, value));
        }

        if (Objects.nonNull(issuedAtSeconds)) {
            jwtBuilder.setIssuedAt(Date.from(Instant.ofEpochSecond(issuedAtSeconds)));
        }

        if (Objects.nonNull(expiryInSeconds)) {
            jwtBuilder.setIssuedAt(Date.from(Instant.ofEpochSecond(expiryInSeconds)));
        }

        jwtBuilder.signWith(SignatureAlgorithm.HS256, secretBytes);
        String jwtToken = jwtBuilder.compact();
        return jwtToken;
    }


    /**
     * @param claimKey
     * @param claimValue
     * @param claimType
     */
    private void assertClaimType(String claimKey, Object claimValue, Class claimType) {
        boolean
            validClaimType =
            claimType.isInstance(claimValue) || claimType == Long.class && claimValue instanceof Integer;
        if (!validClaimType) {
            String
                errorMessage =
                "Expected type: " + claimType.getCanonicalName() + " for claim: '" + claimKey
                + "', but provided value: " + claimValue + " of type: " + claimValue.getClass().getCanonicalName();
            throw new JwtException(errorMessage);
        }
    }

    /**
     * @param jwtToken
     * @param signingKeyResolver
     * @return
     */
    public static Jws<Claims> parseJWTToken(final String jwtToken, final SigningKeyResolver signingKeyResolver) {
        return Jwts.parser()
            .setSigningKeyResolver(signingKeyResolver)
            .parseClaimsJws(jwtToken);
    }

    /**
     * @param token
     * @return
     */
    public static String decodeJWTToken(String token) {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String[] chunks = token.split("\\.");
        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));
        return header + " " + payload;
    }

    /**
     * @param token
     * @param secretKey
     * @return
     * @throws Exception
     */
    public static String decodeJWTToken(String token, String secretKey) throws Exception {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String[] tokenChunks = token.split("\\.");
        String header = new String(decoder.decode(tokenChunks[0]));
        String payload = new String(decoder.decode(tokenChunks[1]));

        String tokenWithoutSignature = tokenChunks[0] + "." + tokenChunks[1];
        String signature = tokenChunks[2];

        final SignatureAlgorithm algorithm = HS256;
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), algorithm.getJcaName());
        DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(algorithm, secretKeySpec);

        if (!validator.isValid(tokenWithoutSignature, signature)) {
            throw new Exception("Could not verify JWT token integrity!");
        }

        return header + " " + payload;
    }
}
