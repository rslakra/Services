package com.rslakra.healthcare.routinecheckup.utils.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:39 PM
 */
@AllArgsConstructor
@Getter
public enum RoleNames {

    USER("USER"),
    ADMIN("ADMIN");

    private String value;

}
