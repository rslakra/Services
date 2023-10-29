package com.rslakra.userservice.persistence.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 12/5/22 19:56 AM
 */
@Getter
@Setter
@NoArgsConstructor
public class User {

    private Long id;
    private String userName;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private UserType userType;
    private String status;
    private String locale;
    private String timeZone;
    private String sub;
    private List<String> roles;
    private String resourceId;
    private String resourceType;
    private UserDetail userDetail;
    private String emailStatus;
    private String sourceId;
    private SourceType sourceType;
}
