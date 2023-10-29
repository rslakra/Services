package com.rslakra.services.automobile.service.security;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class PasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordAuthenticationToken.class);

    private final String domain;

    /**
     * @param principal
     * @param credentials
     * @param domain
     */
    public PasswordAuthenticationToken(Object principal, Object credentials, String domain) {
        super(principal, credentials);
        this.domain = domain;
        super.setAuthenticated(false);
        LOGGER.debug("PasswordAuthenticationToken({}, {}, {})", principal, credentials, domain);
    }

    /**
     * @param principal
     * @param credentials
     * @param domain
     * @param authorities
     */
    public PasswordAuthenticationToken(Object principal, Object credentials, String domain,
                                       Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.domain = domain;
        super.setAuthenticated(true); // must use super, as we override
        LOGGER.debug("PasswordAuthenticationToken({}, {}, {}, {})", principal, credentials, domain, authorities);
    }

}
