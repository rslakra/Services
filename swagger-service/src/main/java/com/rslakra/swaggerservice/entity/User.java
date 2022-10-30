package com.rslakra.swaggerservice.entity;

import com.rslakra.swaggerservice.controller.EntityStatus;
import lombok.Getter;
import lombok.Setter;

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
 * @author Rohtash Lakra (rlakra)
 * @created 8/20/21 7:14 PM
 */
@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity<String> {

    @Column(name = "user_name", length = 64, updatable = false, nullable = false)
    private String userName;

    @Column(name = "password", length = 64, nullable = false)
    private String password;

    @Column(name = "email", length = 64, updatable = false, nullable = false)
    private String email;

    @Column(name = "first_name", length = 64, nullable = false)
    private String firstName;

    @Column(name = "middle_name", length = 64)
    private String middleName;

    @Column(name = "last_name", length = 64, nullable = false)
    private String lastName;

    @Column(name = "status", length = 8, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private EntityStatus status = EntityStatus.INACTIVE;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

}
