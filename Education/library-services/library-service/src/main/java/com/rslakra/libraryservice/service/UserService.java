package com.rslakra.libraryservice.service;

import com.rslakra.libraryservice.persistence.entity.User;
import com.rslakra.libraryservice.enums.RoleType;

import java.util.List;
import java.util.Set;

/**
 * @author Rohtash Lakra
 * @created 8/20/21 8:11 PM
 */
public interface UserService extends BaseService<User> {

    /**
     *
     * @param userName
     * @return
     */
    public User getByUserName(String userName);

    /**
     * Returns the list of users by firstName.
     *
     * @param firstName
     * @return
     */
    public List<User> getByFirstName(String firstName);

    /**
     * Returns the list of users by lastName.
     *
     * @param lastName
     * @return
     */
    public List<User> getByLastName(String lastName);

    /**
     * Returns the list of users by email.
     *
     * @param email
     * @return
     */
    public User getByEmail(String email);

    /**
     * Returns the list of users by email.
     *
     * @param emailList
     * @return
     */
    public List<User> getByEmails(List<String> emailList);

    /**
     * @param user
     * @param roleTypes
     * @return
     */
    public void addRoles(User user, RoleType... roleTypes);

    /**
     * Returns true if the <code>user</code> has the provided <code>roleTypes</code> otherwise false.
     *
     * @param user
     * @param roleTypes
     * @return
     */
    public boolean hasRoles(User user, Set<RoleType> roleTypes);

//    /**
//     * Upsert User.
//     *
//     * @param user
//     * @return
//     */
//    public User upsert(User user);
//
//    /**
//     * Upsert Users.
//     *
//     * @param users
//     * @return
//     */
//    public List<User> upsertUsers(List<User> users);

    /**
     * @param userId
     */
    public void delete(Long userId);
}
