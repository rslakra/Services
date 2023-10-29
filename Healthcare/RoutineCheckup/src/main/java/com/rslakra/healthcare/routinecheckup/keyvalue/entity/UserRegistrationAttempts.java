package com.rslakra.healthcare.routinecheckup.keyvalue.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

import java.util.Date;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:12 PM
 */
@KeySpace("user_registration_attempts")
@Getter
@Setter
public class UserRegistrationAttempts {

    @Id
    private String userIp;

    private Date lastAttemptDate;

    private Integer currentAttemptsCount;

    public UserRegistrationAttempts(String userIp) {
        this.userIp = userIp;
        lastAttemptDate = new Date();
        currentAttemptsCount = 1;
    }

}
