package com.rslakra.libraryclient.entity;

import com.devamatre.framework.spring.persistence.entity.AbstractEntity;
import com.rslakra.libraryclient.controller.EntityStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Rohtash Lakra
 * @created 8/20/21 7:14 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class User extends AbstractEntity<User> implements Serializable {

    private String userName;
    private String password;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private EntityStatus status = EntityStatus.INACTIVE;
    private Set<Role> roles = new HashSet<>();

}
