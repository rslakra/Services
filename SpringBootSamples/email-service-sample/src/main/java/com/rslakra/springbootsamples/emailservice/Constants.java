package com.rslakra.springbootsamples.emailservice;

import java.util.UUID;

/**
 * @author Rohtash Lakra
 * @created 11/29/21 2:37 PM
 */
public interface Constants {

    String SALT_PRE_STRING = "r";
    String SALT_POST_STRING = "l";
    String LOGIN_PAGE = "loginPage";
    String HOME_PAGE_URL = "/home";
    String LOGOUT_URL = "/logout";
    String URL_OWNER_HOME = "/home";
    String URL_LOGIN = "/login";
    String URL_USER_HOME = "/dashboard";
    String MSG_BAD_LOGIN_INPUT = "Bad Login Input!";
    String ADMIN_ROLE_ID = "adminRoleId";
    Object MSG_LOGOUT_SUCCESS = "Logout successfully!";
    Object MSG_INVALID_USER = "Invalid User!";
    String URL_ERROR = "/error";
    String MSG_REQUIRED_USERNAME = "Missing Username!";
    String MSG_REQUIRED_PASSWORD = "Missing Password!";

    /**
     * @return
     */
    static String getUniqueId() {
        return UUID.randomUUID().toString();
    }

    /**
     * @param uuid
     * @return
     */
    static String getSalt(final String uuid) {
        return String.format("%s#%s#%s", SALT_PRE_STRING, uuid, SALT_POST_STRING);
    }

}
