package com.rslakra.jwtauthentication.security.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {

    // validity in milliseconds (1 hour)
    private long validityInMillis = 3600000;
    private String secretKey = "secret";

    public long getValidityInMillis() {
        return validityInMillis;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
