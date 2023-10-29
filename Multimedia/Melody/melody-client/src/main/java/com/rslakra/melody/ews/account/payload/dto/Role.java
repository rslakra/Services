package com.rslakra.melody.ews.account.payload.dto;

import com.devamatre.framework.core.ToString;
import com.devamatre.framework.core.enums.EntityStatus;
import com.devamatre.framework.core.enums.RoleType;
import com.devamatre.framework.spring.payload.dto.NamedEntityDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author Rohtash Lakra
 * @created 8/20/21 7:11 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class Role extends NamedEntityDTO<Long> {

    @Enumerated(value = EnumType.STRING)
    private EntityStatus status = EntityStatus.INACTIVE;

    /**
     * @param roleType
     */
    public Role(final RoleType roleType) {
        setName(roleType.name());
    }

    /**
     * Returns the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(Role.class)
            .add("name", getName())
            .add("status", getStatus())
            .toString();
    }

    /**
     * Returns the set of <code>RoleType</code> from the set of <code>Role</code> type.
     *
     * @param roles
     * @return
     */
    public static Set<RoleType> ofRoles(final Set<Role> roles) {
        return (Objects.isNull(roles) ? Collections.EMPTY_SET
                                      : roles.stream().map(role -> RoleType.ofString(role.getName()))
                    .collect(Collectors.toSet()));
    }

    /**
     * Returns the set of <code>Role</code> from the set of <code>RoleType</code> type.
     *
     * @param roleTypes
     * @return
     */
    public static Set<Role> ofRoleTypes(final Set<RoleType> roleTypes) {
        return (Objects.isNull(roleTypes) ? Collections.EMPTY_SET
                                          : roleTypes.stream().map(roleType -> new Role(roleType))
                    .collect(Collectors.toSet()));
    }
}
