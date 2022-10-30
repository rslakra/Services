package com.rslakra.libraryclient.controller;


import com.rslakra.libraryclient.api.UserService;
import com.rslakra.libraryclient.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 10/9/21 4:50 PM
 */
@RestController
@RequestMapping("${apiPrefix}/users")
public class UserController {

    private final UserService userService;

    /**
     * @param userService
     */
    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    /**
     * @return
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    /**
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getById(userId);
    }

    /**
     * Returns the list of users by userName.
     *
     * @param userName
     * @return
     */
    @GetMapping("/byUserName/{userName}")
    public List<User> getByUserName(@PathVariable String userName) {
        return userService.getByUserName(userName);
    }

    /**
     * Returns the list of users by firstName.
     *
     * @param firstName
     * @return
     */
    @GetMapping("/byFirstName/{firstName}")
    public List<User> getByFirstName(@PathVariable String firstName) {
        return userService.getByFirstName(firstName);
    }

    /**
     * Returns the list of users by lastName.
     *
     * @param lastName
     * @return
     */
    @GetMapping("/byLastName/{lastName}")
    public List<User> getByLastName(@PathVariable String lastName) {
        return userService.getByLastName(lastName);
    }

    /**
     * Returns the list of users by email.
     *
     * @param email
     * @return
     */
    @GetMapping("/byEmail/{email}")
    public User getByEmail(@PathVariable String email) {
        return userService.getByEmail(email);
    }

//    /**
//     * Upsert User.
//     *
//     * @param user
//     * @return
//     */
//    @PostMapping
//    public User save(@RequestBody User user) {
//        return userService.saveUser(user);
//    }
//
//    /**
//     * Upsert Users.
//     *
//     * @param users
//     * @return
//     */
//    @PostMapping
//    public List<User> saveUsers(@RequestBody List<User> users) {
//        return userService.saveUsers(users);
//    }

    /**
     * @param userId
     */
    @DeleteMapping("/{userId}")
    public void delete(@PathVariable Long userId) {
        userService.delete(userId);
    }
}
