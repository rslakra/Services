package com.rslakra.healthcare.routinecheckup.dto.security;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:07 PM
 */
@RequiredArgsConstructor
public class JwtTokenAuthentication implements Authentication {

    private final UserDetails principal;

    @Getter
    private final String token;

    private boolean authenticated = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (principal == null) {
            return null;
        }
        return principal.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        if (principal == null) {
            return null;
        }
        return principal.getPassword();
    }

    @Override
    public Object getDetails() {
        return principal;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated)
        throws IllegalArgumentException {
        authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        if (principal == null) {
            return null;
        }
        return principal.getUsername();
    }
}
