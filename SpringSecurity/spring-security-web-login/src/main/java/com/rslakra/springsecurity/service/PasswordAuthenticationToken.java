package com.rslakra.springsecurity.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class PasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private String domain;

    public PasswordAuthenticationToken(Object principal, Object credentials, String domain) {
        super(principal, credentials);
        this.domain = domain;
        super.setAuthenticated(false);
    }

    public PasswordAuthenticationToken(Object principal, Object credentials, String domain,
                                       Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.domain = domain;
        super.setAuthenticated(true); // must use super, as we override
    }

    public String getDomain() {
        return this.domain;
    }
}
