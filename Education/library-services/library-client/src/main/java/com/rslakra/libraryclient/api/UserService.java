package com.rslakra.libraryclient.api;

import com.rslakra.libraryclient.entity.User;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 10/9/21 5:29 PM
 */
public interface UserService extends BaseService<User> {

    String USERS = "/users";
    String GET_USER_BY_ID = "/users/{userId}";
    String GET_USERS_BY_USER_NAME = "/users/byUserName/{userName}";
    String GET_USERS_BY_FIRST_NAME = "/users/byFirstName/{firstName}";
    String GET_USERS_BY_LAST_NAME = "/users/byFirstName/{firstName}";
    String GET_USER_BY_EMAIL = "/users/byEmail/{email}";

    /**
     * @return
     */
    List<User> getUsers();

    /**
     * Returns the list of users by userName.
     *
     * @param userName
     * @return
     */
    List<User> getByUserName(String userName);

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
     * @param email
     * @return
     */
//    public List<User> getByEmail(String email);
    User getByEmail(String email);

    /**
     * Upsert User.
     *
     * @param user
     * @return
     */
    User saveUser(User user);

    /**
     * Upsert Users.
     *
     * @param users
     * @return
     */
    List<User> saveUsers(List<User> users);

    /**
     * @param userId
     */
    void delete(Long userId);
}
