package com.rslakra.services.automobile.service.security.context;

import com.devamatre.framework.core.BeanUtils;
import com.rslakra.services.automobile.domain.entities.AutoUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Rohtash Lakra
 * @created 4/20/23 9:57 AM
 */
public enum ContextUtils {
    INSTANCE;

    public static final String HASH_CODE = "hashCode";
    public static final String X_FORWARDED_FOR = "X-FORWARDED-FOR";

    /**
     * @param principal
     * @param credentials
     * @param authorities
     * @return
     */
    public static Authentication authenticate(Object principal, Object credentials,
                                              Collection<? extends GrantedAuthority> authorities) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, credentials, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    /**
     * @return
     */
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * @return
     */
    public static AutoUser getLoggedInUser() {
        final Authentication authentication = INSTANCE.getAuthentication();
        return (AutoUser) ((authentication.getPrincipal() instanceof AutoUser) ? authentication.getPrincipal() : null);
    }

    /**
     * @return
     */
    public static String getLoggedInUsername() {
        return INSTANCE.getAuthentication().getName();
    }

    /**
     * @return
     */
    public static boolean isLoggedIn() {
        try {
            return (!"anonymousUser".equals(getLoggedInUsername()));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @param authentication
     * @return
     */
    public static String getAuthenticatedPassword(Authentication authentication) {
        return ((BeanUtils.isNotNull(authentication) && BeanUtils.isNotNull(authentication.getCredentials()))
                ? authentication.getCredentials().toString()
                : null);
    }

    /**
     * @param autoUser
     * @return
     */
    public static Authentication authenticate(AutoUser autoUser) {
        return authenticate(autoUser, autoUser.getPassword(), autoUser.getAuthorities());
    }

    /**
     * @param authentication
     * @return
     */
    public static boolean isAnonymousAuthToken(Authentication authentication) {
        return (Objects.nonNull(authentication) && authentication.getClass()
            .equals(AnonymousAuthenticationToken.class));
    }

    /**
     * @return
     */
    public static Optional<String> getDomain() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String domain = null;
        if (!isAnonymousAuthToken(authentication)) {
            AutoUser autoUser = (AutoUser) authentication.getPrincipal();
//            domain = autoUser.getDomain();
        }

        return Optional.ofNullable(domain);
    }
}
