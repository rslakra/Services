package com.rslakra.jwtauthentication1.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rslakra.jwtauthentication1.persistence.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsImpl.class);

    private Long id;
    private String userName;
    @JsonIgnore
    private String password;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String userName, String password, String email,
                           Collection<? extends GrantedAuthority> authorities) {
        LOGGER.debug("UserDetailsImpl({}, {}, {}, {}, {})", id, userName, password, email, authorities);
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        LOGGER.debug("build({})", user);
        final List<GrantedAuthority> authorities = user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getName().name()))
            .collect(Collectors.toList());

        return new UserDetailsImpl(user.getId(), user.getUserName(), user.getPassword(), user.getEmail(), authorities);
    }

    public Long getId() {
        return id;
    }

    /**
     * @return
     */
    @Override
    public String getUsername() {
        return userName;
    }

    /**
     * @return
     */
    @Override
    public String getPassword() {
        return password;
    }


    /**
     * @return
     */
    public String getEmail() {
        return email;
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

    /**
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        return Objects.equals(id, ((UserDetailsImpl) object).id);
    }
}
