package com.rslakra.springbootsamples.jwtauthentication.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rslakra.springbootsamples.jwtauthentication.persistence.model.IdentityDO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public final class UserPrinciple implements UserDetails {

    private IdentityDO identity;
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * @param identity
     * @param authorities
     */
    public UserPrinciple(final IdentityDO identity, Collection<? extends GrantedAuthority> authorities) {
        this.identity = identity;
        this.authorities = authorities;
    }

    /**
     * @param identity
     * @return
     */
    public static UserPrinciple build(IdentityDO identity) {
        final List<GrantedAuthority>
            authorities =
            identity.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
        return new UserPrinciple(identity, authorities);
    }

    @Override
    public String getUsername() {
        return identity.getUserName();
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return identity.getPassword();
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
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        UserPrinciple userPrinciple = (UserPrinciple) object;
        return Objects.equals(identity.getId(), userPrinciple.getIdentity().getId());
    }
}
