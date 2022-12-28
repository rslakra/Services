package com.rslakra.userservice.service;

import com.rslakra.userservice.persistence.domain.User;
import com.rslakra.userservice.persistence.domain.UserDetail;
import com.rslakra.userservice.persistence.domain.UserType;

/**
 * @author Rohtash Lakra
 * @created 12/5/22 20:12 PM
 */
public class UserBuilder {

    private final User user = new User();
    private String alertName;
    private String userName;

    public UserBuilder setAlertType(UserType userType) {
        user.setUserType(userType);
        return this;
    }

    public UserBuilder setAlertParams(UserDetail userDetail) {
        user.setUserDetail(userDetail);
        return this;
    }

}
