package com.rslakra.melody.ews.account.service;

import com.rslakra.melody.ews.account.payload.dto.User;
import com.rslakra.melody.ews.framework.client.AbstractClientService;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 8/20/21 8:11 PM
 */
public interface UserService extends AbstractClientService<User> {

    String USERS = "users";
    String USERS_BATCH = "users/batch";
    String USER_BY_ID = "users/{id}";
    String USERS_FILTER = "users/filter";

    /**
     * Returns the list of users by email.
     *
     * @param email
     * @return
     */
    User getByEmail(String email);

    /**
     * Returns the list of users by firstName.
     *
     * @param firstName
     * @return
     */
    List<User> getByFirstName(String firstName);

    /**
     * Returns the list of users by lastName.
     *
     * @param lastName
     * @return
     */
    List<User> getByLastName(String lastName);

    /**
     * Returns the list of users by email.
     *
     * @param emails
     * @return
     */
    public List<User> getByEmails(List<String> emails);

}
