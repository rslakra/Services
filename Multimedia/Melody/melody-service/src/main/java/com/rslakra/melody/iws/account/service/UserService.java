package com.rslakra.melody.iws.account.service;

import com.devamatre.framework.core.enums.RoleType;
import com.devamatre.framework.spring.service.AbstractService;
import com.rslakra.melody.iws.account.persistence.entity.User;

import java.util.List;
import java.util.Set;

/**
 * @author Rohtash Lakra
 * @created 8/20/21 8:11 PM
 */
public interface UserService extends AbstractService<User, Long> {

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

}
