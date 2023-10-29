package com.rslakra.springsecurity.javajwt.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.rslakra.springsecurity.javajwt.service.SecretsService;
import com.rslakra.springsecurity.javajwt.utils.JWTUtils;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class JWTUtilsTest {

    private final SecretsService secretsService = new SecretsService();


    /**
     * @param expiryInMinutes
     * @return
     */
    private static Map<String, Object> buildClaims(long expiryInMinutes) {
        final Map<String, Object> claims = new HashMap<>();
        claims.put(JWTUtils.ISSUER, "Rohtash Lakra");
        claims.put(JWTUtils.SUBJECT, "jwtToken");
        claims.put("name", "Rohtash Lakra");
        claims.put("scope", "ADMIN");
        claims.put(JWTUtils.ISSUED_AT, Instant.now().getEpochSecond());
        claims.put(JWTUtils.EXPIRATION, Instant.now().plus(expiryInMinutes, ChronoUnit.MINUTES));
        return claims;
    }

    @Test
    public void testGenerateToken() {
        String jwtToken = JWTUtils.jwtCompactBuilderWithClaims(buildClaims(30), secretsService.getHS256SecretBytes());
        assertThat(jwtToken).isNotNull();
    }

    private final static String
        SIMPLE_TOKEN =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkJhZWxkdW5nIFVzZXIiLCJpYXQiOjE1MTYyMzkwMjJ9";
    private final static String
        SIGNED_TOKEN =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkJhZWxkdW5nIFVzZXIiLCJpYXQiOjE1MTYyMzkwMjJ9.qH7Zj_m3kY69kxhaQXTa-ivIpytKXXjZc1ZSmapZnGE";

    @Test
    void givenSimpleToken_whenDecoding_thenStringOfHeaderPayloadAreReturned() {
        assertThat(JWTUtils.decodeJWTToken(SIMPLE_TOKEN))
            .contains(SignatureAlgorithm.HS256.getValue());
    }

    @Test
    void givenSignedToken_whenDecodingWithInvalidSecret_thenIntegrityIsNotValidated() {
        assertThatThrownBy(() -> JWTUtils.decodeJWTToken(SIGNED_TOKEN, "BAD_SECRET"))
            .hasMessage("Could not verify JWT token integrity!");
    }

    @Test
    void givenSignedToken_whenDecodingWithValidSecret_thenIntegrityIsValidated() throws Exception {
        assertThat(JWTUtils.decodeJWTToken(SIGNED_TOKEN, "MySecretKey"))
            .contains("Lakra User");
    }
}
