package com.rslakra.springbootsamples.emailservice.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rohtash Lakra
 * @created 1/6/22 5:18 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class UserInfo {

    private String id;
    private String userName;
    private String salt;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String roleId;
}
