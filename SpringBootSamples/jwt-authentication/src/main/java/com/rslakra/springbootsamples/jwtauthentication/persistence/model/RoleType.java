package com.rslakra.springbootsamples.jwtauthentication.persistence.model;

/**
 *
 */
public enum RoleType {

    ADMIN,
    GUEST,
    MANAGER,
    USER;

    /**
     * @param roleType
     * @return
     */
    public static String toRoleName(final RoleType roleType) {
        return String.format("ROLE_%s", roleType.name());
    }

}
