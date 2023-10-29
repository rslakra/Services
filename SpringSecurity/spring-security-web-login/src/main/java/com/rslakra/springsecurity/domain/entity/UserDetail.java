package com.rslakra.springsecurity.domain.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserDetail extends User {

    private static final long serialVersionUID = 1L;

    private String domain;

    public UserDetail(String username, String domain, String password, boolean enabled,
                      boolean accountNonExpired, boolean credentialsNonExpired,
                      boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.domain = domain;
    }

    /**
     * @param username
     * @param password
     * @param authorities
     */
    public UserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, true, true, true, true, authorities);
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }
}
