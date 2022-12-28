package com.rslakra.springbootsamples.springbootsample.domain.user;

import com.rslakra.springbootsamples.springbootsample.service.UserStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 8/12/21 3:23 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class IdentityDO {

    private String id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private UserStatus status;
    private Date createdAt;
    private Date updatedAt;

}
