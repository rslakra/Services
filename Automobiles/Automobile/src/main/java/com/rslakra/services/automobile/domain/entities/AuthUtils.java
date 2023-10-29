/**
 *
 */
package com.rslakra.services.automobile.domain.entities;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.core.enums.RoleType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;

/**
 * @author Rohtash Lakra (rohtash.singh@gmail.com)
 * @since 01-03-2020 4:03:58 PM
 */
public enum AuthUtils {
    INSTANCE;

    /**
     * @param roleType
     * @return
     */
    public static GrantedAuthority getAuthority(RoleType roleType) {
        return new SimpleGrantedAuthority(roleType.name());
    }

    /**
     * @param roleTypes
     * @return
     */
    public static List<GrantedAuthority> getAuthorities(RoleType... roleTypes) {
        if (BeanUtils.isNotEmpty(roleTypes)) {
            final String[] roles = new String[roleTypes.length];
            for (int i = 0; i < roleTypes.length; i++) {
                roles[i] = roleTypes[i].name();
            }
            return AuthorityUtils.createAuthorityList(roles);
        }

        return Collections.EMPTY_LIST;
    }

}