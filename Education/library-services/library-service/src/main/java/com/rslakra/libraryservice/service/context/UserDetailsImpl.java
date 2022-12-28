//package com.rslakra.libraryservice.service;
//
//import com.rslakra.libraryservice.entity.Role;
//import com.rslakra.libraryservice.entity.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Set;
//
///**
// * @author Rohtash Lakra
// * @created 8/20/21 6:18 PM
// */
//public class UserDetailsImpl implements UserDetails {
//
//    private User user;
//
//    /**
//     * @param user
//     */
//    public UserDetailsImpl(User user) {
//        this.user = user;
//    }
//
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<Role> roles = user.getRoles();
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        for (Role role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
//
//        return authorities;
//    }
//
//    /**
//     * @return
//     */
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    /**
//     * @return
//     */
//    @Override
//    public String getUsername() {
//        return user.getEmail();
//    }
//
//    /**
//     * @return
//     */
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    /**
//     * @return
//     */
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    /**
//     * @return
//     */
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    /**
//     * @return
//     */
//    @Override
//    public boolean isEnabled() {
//        return user.getActive();
//    }
//
//}
