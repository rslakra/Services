package com.rslakra.swaggerservice.service;

import com.devamatre.framework.spring.exception.NoRecordFoundException;
import com.rslakra.swaggerservice.persistence.entity.User;
import com.rslakra.swaggerservice.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Rohtash Lakra
 * @version 1.0.0
 * @since Aug 08, 2021 15:28:23
 */
@Service
public class UserService {

    // userRepository
    private final UserRepository userRepository;

    /**
     * @param userRepository
     */
    @Autowired
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @return
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * @param userId
     * @return
     */
    public User getUserById(final Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new NoRecordFoundException("userId:%d", userId));
    }

    /**
     * Returns the list of users by userName.
     *
     * @param userName
     * @return
     */
    public List<User> getByUserName(String userName) {
        Objects.requireNonNull(userName);
        return userRepository.getByUserName(userName);
    }

    /**
     * Returns the list of users by firstName.
     *
     * @param firstName
     * @return
     */
    public List<User> getByFirstName(String firstName) {
        Objects.requireNonNull(firstName);
        return userRepository.getByFirstName(firstName);
    }

    /**
     * Returns the list of users by lastName.
     *
     * @param lastName
     * @return
     */
    public List<User> getByLastName(String lastName) {
        Objects.requireNonNull(lastName);
        return userRepository.getByLastName(lastName);
    }

    /**
     * Returns the list of users by email.
     *
     * @param email
     * @return
     */
//    public List<User> getByEmail(String email) {
//        Objects.requireNonNull(email);
//        return userRepository.getByEmail(email);
//    }
    public User getByEmail(String email) {
        Objects.requireNonNull(email);
        return userRepository.getByEmail(email);
    }

    /**
     * @param pageable
     * @return
     */
    public Page<User> getByFilter(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    /**
     * Upsert User.
     *
     * @param user
     * @return
     */
    public User upsert(User user) {
        Objects.requireNonNull(user);
        User oldUser = user;
        if (!Objects.isNull(user.getId())) {
            oldUser =
                userRepository.findById(user.getId())
                    .orElseThrow(() -> new NoRecordFoundException("userId:%d", user.getId()));

            // update user
            oldUser.setUserName(user.getUserName());
            oldUser.setPassword(user.getPassword());
            oldUser.setFirstName(user.getFirstName());
            oldUser.setLastName(user.getLastName());
            oldUser.setEmail(user.getEmail());
        }

        // persist user
        oldUser = userRepository.save(oldUser);
        return oldUser;
    }

    /**
     * Upsert Users.
     *
     * @param users
     * @return
     */
    public List<User> upsert(List<User> users) {
        Objects.requireNonNull(users);
        List<User> userList = new ArrayList<>();
        users.forEach(user -> userList.add(upsert(user)));
        return userList;
    }

    /**
     * @param userId
     */
    public void delete(Long userId) {
        Objects.requireNonNull(userId);
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new NoRecordFoundException("userId:%d", userId));
        userRepository.delete(user);
    }
}
