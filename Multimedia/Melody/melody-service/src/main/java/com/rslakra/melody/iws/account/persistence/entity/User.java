package com.rslakra.melody.iws.account.persistence.entity;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.core.ToString;
import com.devamatre.framework.core.enums.EntityStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Rohtash Lakra
 * @created 8/20/21 7:14 PM
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends Person {

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private EntityStatus status = EntityStatus.INACTIVE;

    //    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Transient
    private Set<Role> roles = new HashSet<>();

    /**
     * Adds the <code>Set<Role></code>.
     *
     * @param userRoles
     */
    public void addRoles(final Set<Role> userRoles) {
        if (!BeanUtils.isEmpty(userRoles)) {
            getRoles().addAll(userRoles);
        }
    }

    /**
     * @param userRoles
     * @return
     */
    public boolean hasRoles(final Set<Role> userRoles) {
        /*
         * The Collections.disjoint(A, B) returns true if the two specified collections have no elements in common
         * otherwise returns false if the collections contains any common elements.
         */
        return ((BeanUtils.isNotEmpty(getRoles()) && BeanUtils.isNotEmpty(userRoles))
                && Collections.disjoint(getRoles(), userRoles));
    }

    /**
     * Returns the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(User.class)
            .add("email", getEmail())
            .add("password", getPassword())
            .add("firstName", getFirstName())
            .add("middleName", getMiddleName())
            .add("lastName", getLastName())
            .add("status", getStatus())
            .add("roles", getRoles())
            .toString();
    }
}
