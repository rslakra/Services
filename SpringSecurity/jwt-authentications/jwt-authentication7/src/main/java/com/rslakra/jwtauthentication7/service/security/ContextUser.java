package com.rslakra.jwtauthentication7.service.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rslakra.jwtauthentication7.persistence.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public final class ContextUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String username;
    private String email;
    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public ContextUser(Long id, String name, String username, String email, String password,
                       Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    /**
     * @param user
     * @return
     */
    public static ContextUser build(User user) {
//        List<GrantedAuthority> authorities = user.getRoles().stream()
//            .map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
        return new ContextUser(user.getId(), user.getUserName(), user.getUserName(), user.getUserName(),
                               user.getPassword(), new ArrayList<>());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        return Objects.equals(id, ((ContextUser) object).getId());
    }
}
