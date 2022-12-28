package com.rslakra.enablemethodsecurity.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ContextUser implements UserDetails {

    private String userName;
    private String password;
    private List<GrantedAuthority> grantedAuthorities;
    private boolean accessToRestrictedPolicy;
    private boolean accountNonExpired = false;
    private boolean accountNonLocked = false;
    private boolean credentialsNonExpired = false;
    private boolean enabled = false;

    /**
     * @param restrictedPolicy
     * @return
     */
    public ContextUser withAccessToRestrictedPolicy(boolean restrictedPolicy) {
        this.accessToRestrictedPolicy = restrictedPolicy;
        return this;
    }

    /**
     * @return
     */
    public boolean hasAccessToRestrictedPolicy() {
        return accessToRestrictedPolicy;
    }

    /**
     * @param grantedAuthorities
     * @return
     */
    public ContextUser withGrantedAuthorities(List<GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
        return this;
    }

    /**
     * @param roles
     * @return
     */
    public ContextUser withRoles(String... roles) {
        return withGrantedAuthorities(fromRoles(roles));
    }

    /**
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    /**
     * @param password
     * @return
     */
    public ContextUser withPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * @return
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * @param userName
     * @return
     */
    public ContextUser withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @return
     */
    public static ContextUser builder() {
        return new ContextUser();
    }

    /**
     * @param roles
     * @return
     */
    public static List<GrantedAuthority> fromRoles(String... roles) {
        return Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
