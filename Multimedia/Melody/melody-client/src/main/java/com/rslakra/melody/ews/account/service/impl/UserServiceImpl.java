package com.rslakra.melody.ews.account.service.impl;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.core.Payload;
import com.devamatre.framework.spring.client.ApiRestClient;
import com.devamatre.framework.spring.exception.InvalidRequestException;
import com.devamatre.framework.spring.persistence.Operation;
import com.rslakra.melody.ews.account.payload.dto.User;
import com.rslakra.melody.ews.account.service.UserService;
import com.rslakra.melody.ews.framework.client.impl.AbstractClientServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Rohtash Lakra
 * @created 10/9/21 5:50 PM
 */
@Service
public class UserServiceImpl extends AbstractClientServiceImpl<User> implements UserService {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);


    // apiRestClient
    private final ApiRestClient apiRestClient;

    /**
     * @param apiRestClient
     */
    @Autowired
    public UserServiceImpl(final ApiRestClient apiRestClient) {
        LOGGER.debug("UserServiceImpl({})", apiRestClient);
        this.apiRestClient = apiRestClient;
    }

    /**
     * Validates the <code>T</code> object.
     *
     * @param operation
     * @param user
     * @return
     */
    @Override
    public User validate(Operation operation, User user) {
        switch (operation) {
            case CREATE:
                if (BeanUtils.isEmpty(user.getEmail())) {
                    throw new InvalidRequestException("The user's email should provide!");
                } else if (BeanUtils.isEmpty(user.getFirstName())) {
                    throw new InvalidRequestException("The user's firstName should provide!");
                } else if (BeanUtils.isEmpty(user.getLastName())) {
                    throw new InvalidRequestException("The user's lastName should provide!");
                }
                break;

            case UPDATE:
                if (BeanUtils.isNull(user.getId())) {
                    throw new InvalidRequestException("The user's ID should provide!");
                }

                break;

            default:
                throw new InvalidRequestException("Unsupported Operation!");
        }

        return user;
    }

    /**
     * Creates the <code>T</code> object.
     *
     * @param user
     * @return
     */
    @Override
    public User create(User user) {
        if (BeanUtils.isNull(user)) {
            throw new InvalidRequestException("The user should provide!");
        }

        validate(Operation.CREATE, user);
        user = apiRestClient.doPost(USERS, user, User.class);
        return user;
    }

    /**
     * Creates the <code>List<T></code> objects.
     *
     * @param users
     * @return
     */
    @Override
    public List<User> create(List<User> users) {
        if (BeanUtils.isEmpty(users)) {
            throw new InvalidRequestException("The users should provide!");
        }

        users.forEach(user -> validate(Operation.CREATE, user));
        users = Arrays.asList(apiRestClient.doPost(USERS_BATCH, users, User[].class));
        return users;
    }

    /**
     * Returns the list of all <code>T</code> objects.
     *
     * @return
     */
    @Override
    public List<User> getAll() {
        LOGGER.debug("+getAll()");
        // note: get results by array and convert to list.
        List<User> users;
        // helps to display empty ui page.
        try {
            users = Arrays.asList(apiRestClient.doGet(USERS, User[].class));
        } catch (Exception ex) {
            LOGGER.error(ex.getLocalizedMessage(), ex);
            // helps to display empty ui page.
            users = new ArrayList<>();
        }

        LOGGER.debug("-getAll(), users: {}", users);
        return users;
    }

    /**
     * @param filters
     * @return
     */
    @Override
    public List<User> getByFilter(Map<String, Object> filters) {
        LOGGER.debug("+getByFilter({})", filters);
        // rest/users/filter?id=3
        final User[] users = apiRestClient.doGet(USERS_FILTER, User[].class, filters);
        LOGGER.debug("-getByFilter(), users:{}", users);
        return Arrays.asList(users);
    }

    /**
     * @param filters
     * @param pageable
     * @return
     */
    @Override
    public List<User> getByFilter(Map<String, Object> filters, Pageable pageable) {
        LOGGER.debug("+getByFilter({}, {})", filters, pageable);
        List<User> users = getByFilter(filters);
        LOGGER.debug("-getByFilter(), users: {}", users);
        return users;
    }

    /**
     * Returns the <code>T</code> object by <code>id</code>.
     *
     * @param id
     * @return
     */
    @Override
    public User getById(Long id) {
        LOGGER.debug("+getById({})", id);
        // rest/users/filter?id=16
        User user = (User) getByFilter(Payload.newBuilder().ofPair("id", id)).stream().findFirst().orElse(null);
        LOGGER.debug("-getById(), user: {}", user);
        return user;
    }

    /**
     * Updates the <code>T</code> object.
     *
     * @param user
     * @return
     */
    @Override
    public User update(User user) {
        LOGGER.debug("+update({})", user);
        if (BeanUtils.isEmpty(user)) {
            throw new InvalidRequestException("The user should provide!");
        }

        validate(Operation.UPDATE, user);
        apiRestClient.doPut(USERS, user, User.class);

        LOGGER.debug("-update(), user:{}", user);
        return user;
    }

    /**
     * Updates the <code>List<T></code> objects.
     *
     * @param users
     * @return
     */
    @Override
    public List<User> update(List<User> users) {
        LOGGER.debug("+update({})", users);
        if (BeanUtils.isEmpty(users)) {
            throw new InvalidRequestException("The users should provide!");
        }

        users.forEach(user -> validate(Operation.UPDATE, user));
        apiRestClient.doPut(USERS_BATCH, users, List.class);

        LOGGER.debug("-update(), users:{}", users);
        return users;
    }

    /**
     * @param id
     */
    public User delete(Long id) {
        LOGGER.debug("+delete({})", id);
        BeanUtils.assertNonNull(id, "The user's id should provide!");
        User user = null;
        apiRestClient.doDelete(USER_BY_ID, Payload.newBuilder().ofPair("id", id));
        LOGGER.debug("-delete(), user:{}", user);
        return user;
    }

    /**
     * Returns the list of users by firstName.
     *
     * @param firstName
     * @return
     */
    public List<User> getByFirstName(String firstName) {
        LOGGER.debug("+getByFirstName({})", firstName);
        BeanUtils.assertNonNull(firstName, "The user's firstName should provide!");
        List<User> users = getByFilter(Payload.newBuilder().ofPair("firstName", firstName));
        LOGGER.debug("-getByFirstName(), users:{}", users);
        return users;
    }

    /**
     * Returns the list of users by lastName.
     *
     * @param lastName
     * @return
     */
    public List<User> getByLastName(String lastName) {
        LOGGER.debug("+getByLastName({})", lastName);
        BeanUtils.assertNonNull(lastName, "The user's lastName should provide!");
        List<User> users = getByFilter(Payload.newBuilder().ofPair("lastName", lastName));
        LOGGER.debug("-getByLastName(), users:{}", users);
        return users;
    }

    /**
     * Returns the user by email.
     *
     * @param email
     * @return
     */
    public User getByEmail(final String email) {
        LOGGER.debug("+getByEmail({})", email);
        BeanUtils.assertNonNull(email, "The user's email should provide!");
        User user = (User) getByFilter(Payload.newBuilder().ofPair("email", email)).stream().findFirst().orElse(null);
        LOGGER.debug("-getByEmail(), user:{}", user);
        return user;
    }

    /**
     * Returns the list of users by email.
     *
     * @param emails
     * @return
     */
    @Override
    public List<User> getByEmails(List<String> emails) {
        LOGGER.debug("+getByEmails({})", emails);
        BeanUtils.assertNonNull(emails, "The user's emails should provide!");
        List<User> users = getByFilter(Payload.newBuilder().ofPair("emails", emails));
        LOGGER.debug("-getByEmails(), users:{}", users);
        return users;
    }

}
