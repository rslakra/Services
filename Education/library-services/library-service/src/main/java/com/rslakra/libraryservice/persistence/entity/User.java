package com.rslakra.libraryservice.persistence.entity;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.core.ToString;
import com.rslakra.libraryservice.enums.EntityStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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

    @Column(name = "user_name", length = 64, unique = true, updatable = false, nullable = false)
    private String userName;

    @Column(name = "password", length = 64, nullable = false)
    private String password;

    @Column(name = "status", length = 8, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private EntityStatus status = EntityStatus.INACTIVE;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    /**
     * Adds the <code>Set<Role></code>.
     *
     * @param userRoles
     */
    public void addRoles(final Set<Role> userRoles) {
        if (BeanUtils.isNotEmpty(userRoles)) {
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
        return ((BeanUtils.isNotEmpty(getRoles()) && BeanUtils.isNotEmpty(userRoles)) && Collections.disjoint(
            getRoles(), userRoles));
    }

    /**
     * Returns the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(User.class)
            .add("userName", getUserName())
            .add("password", getPassword())
            .add("email", getEmail())
            .add("firstName", getFirstName())
            .add("middleName", getMiddleName())
            .add("lastName", getLastName())
            .add("status", getStatus())
            .add("roles", getRoles())
            .toString();
    }
}
