package com.rslakra.libraryclient.service;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.core.Payload;
import com.rslakra.libraryclient.api.BaseService;
import com.rslakra.libraryclient.api.UserService;
import com.rslakra.libraryclient.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Rohtash Lakra
 * @created 8/20/21 8:11 PM
 */
@Service
public class UserServiceImpl implements UserService {

    // restService
    private final RestService restService;

    /**
     * @param restService
     */
    @Autowired
    public UserServiceImpl(final RestService restService) {
        this.restService = restService;
    }

    /**
     * @return
     */
    public List<User> getUsers() {
        // 1. getForObject(url, classType)
        return Arrays.asList(restService.getForObject(USERS, User[].class));

        // 2. getForEntity(url, responseType)
//        return Arrays.asList(restService.getForEntity(USERS, User[].class).getBody());

        // 3. exchange(url, method, requestEntity, responseType)
//        final HttpEntity<String> httpEntity = new HttpEntity<>(BaseService.newHttpHeaders());
//        return Arrays.asList(restService.exchange(USERS, HttpMethod.GET, httpEntity, User[].class).getBody());
    }

    /**
     * @param userId
     * @return
     */
    public User getById(final Long userId) {
        // getForObject
        final Payload params = Payload.newBuilder().ofPair("userId", userId);
        return restService.getForObject(GET_USER_BY_ID, User.class, params);
        // 3. exchange(url, method, requestEntity, responseType)
//        final HttpEntity<String> httpEntity = new HttpEntity<>(BaseService.newHttpHeaders());
//        final String pathSegment = restService.pathSegments(USERS, userId.toString());
//        return restService.exchange(pathSegment, HttpMethod.GET, httpEntity, User.class).getBody();
    }

    /**
     * Returns the list of users by userName.
     *
     * @param userName
     * @return
     */
    public List<User> getByUserName(final String userName) {
        Objects.requireNonNull(userName);
        final Payload params = Payload.newBuilder().ofPair("userName", userName);
        return Arrays.asList(restService.getForObject(GET_USERS_BY_USER_NAME, User[].class, params));
    }

    /**
     * Returns the list of users by firstName.
     *
     * @param firstName
     * @return
     */
    public List<User> getByFirstName(String firstName) {
        Objects.requireNonNull(firstName);
        final Payload params = Payload.newBuilder().ofPair("firstName", firstName);
        return Arrays.asList(restService.getForObject(GET_USERS_BY_FIRST_NAME, User[].class, params));
    }

    /**
     * Returns the list of users by lastName.
     *
     * @param lastName
     * @return
     */
    public List<User> getByLastName(String lastName) {
        Objects.requireNonNull(lastName);
        final Payload params = Payload.newBuilder().ofPair("lastName", lastName);
        return Arrays.asList(restService.getForObject(GET_USERS_BY_LAST_NAME, User[].class, params));
    }

    /**
     * Returns the list of users by email.
     *
     * @param email
     * @return
     */
    public User getByEmail(final String email) {
        Objects.requireNonNull(email);
        final Payload params = Payload.newBuilder().ofPair("email", email);
        return restService.getForObject(GET_USER_BY_EMAIL, User.class, params);
    }

    /**
     * Upsert User.
     *
     * @param user
     * @return
     */
    public User saveUser(User user) {
        Objects.requireNonNull(user);
        user = restService.postForObject(USERS, user, User.class);
        return user;
    }

    /**
     * Upsert Users.
     *
     * @param users
     * @return
     */
    public List<User> saveUsers(List<User> users) {
        Objects.requireNonNull(users);
        users = Arrays.asList(restService.postForObject(USERS, users, User[].class));
        return users;
    }

    /**
     * @param userId
     */
    public void delete(Long userId) {
        Objects.requireNonNull(userId);
        final HttpEntity<String> httpEntity = new HttpEntity<>(BaseService.newHttpHeaders());
        final String pathSegment = BeanUtils.pathSegments(USERS, userId.toString());
        // 3. exchange(url, method, requestEntity, responseType)
        restService.exchange(pathSegment, HttpMethod.DELETE, httpEntity, Void.class);
    }

}
