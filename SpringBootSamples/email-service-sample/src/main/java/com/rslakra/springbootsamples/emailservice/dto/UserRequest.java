package com.rslakra.springbootsamples.emailservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rohtash Lakra
 * @created 11/29/21 2:28 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class UserRequest {

    private String id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

}
