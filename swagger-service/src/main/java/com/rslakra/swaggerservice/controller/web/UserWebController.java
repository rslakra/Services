package com.rslakra.swaggerservice.controller.web;

import com.rslakra.swaggerservice.entity.User;
import com.rslakra.swaggerservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

/**
 * @author: Rohtash Lakra (rlakra)
 * @since: 9/30/19 5:38 PM
 */
@Controller
@RequestMapping("/users")
public class UserWebController {

    // userService
    private final UserService userService;

    /**
     * @param userService
     */
    @Autowired
    public UserWebController(UserService userService) {
        this.userService = userService;
    }

    /**
     * @param user
     * @return
     */
    @PostMapping("/save")
    public String saveUser(User user) {
        user = userService.upsert(user);
        return "redirect:/users/list";
    }

    /**
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String getUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "listUsers";
    }

    /**
     * @param model
     * @param userId
     * @return
     */
    @RequestMapping(path = {"/create", "/update/{userId}"})
    public String upsertUser(Model model, @PathVariable(name = "userId") Optional<Long> userId) {
        User user = null;
        if (userId.isPresent()) {
            user = userService.getUserById(userId.get());
        } else {
            user = new User();
        }
        model.addAttribute("user", user);

        return "upsertUser";
    }

    /**
     * @param model
     * @param userId
     * @return
     */
    @RequestMapping("/delete/{userId}")
    public String delete(Model model, @PathVariable(name = "userId") Long userId) {
        userService.delete(userId);
        return "redirect:/users/list";
    }

}
