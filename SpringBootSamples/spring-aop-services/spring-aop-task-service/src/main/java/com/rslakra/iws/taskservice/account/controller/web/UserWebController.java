package com.rslakra.iws.taskservice.account.controller.web;

import com.devamatre.framework.spring.controller.web.AbstractWebController;
import com.devamatre.framework.spring.filter.Filter;
import com.devamatre.framework.spring.parser.Parser;
import com.rslakra.iws.taskservice.account.persistence.entity.User;
import com.rslakra.iws.taskservice.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author: Rohtash Lakra
 * @since: 9/30/19 5:38 PM
 */
@Controller
@RequestMapping("/users")
public class UserWebController extends AbstractWebController<User, Long> {

    // userService
    private final UserService userService;

    /**
     * @param userService
     */
    @Autowired
    public UserWebController(UserService userService) {
        this.userService = userService;
    }

//    /**
//     * @param id
//     */
//    @Override
//    public void validate(Optional<Long> id) {
//        super.validate(id);
//    }

    /**
     * Saves the <code>t</code> object.
     *
     * @param user
     * @return
     */
    @PostMapping("/save")
    @Override
    public String save(User user) {
        user = userService.create(user);
        return "redirect:/users/list";
    }

    /**
     * Returns the list of <code>T</code> objects.
     *
     * @param model
     * @return
     */
    @GetMapping("/list")
    @Override
    public String getAll(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "users/listUsers";
    }

    /**
     * Filters the list of <code>T</code> objects.
     *
     * @param model
     * @param filter
     * @return
     */
    @GetMapping("/filter")
    @Override
    public String filter(Model model, Filter filter) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "users/listUsers";
    }

    /**
     * @return
     */
    @Override
    public Parser<User> getParser() {
        return null;
    }

    /**
     * @param model
     * @param allParams
     * @return
     */
    @Override
    public String filter(Model model, Map<String, Object> allParams) {
        return null;
    }

    /**
     * @param model
     * @param aLong
     * @return
     */
    @Override
    public String editObject(Model model, Long aLong) {
        return null;
    }

    /**
     * @param model
     * @param userId
     * @return
     */
    @RequestMapping(path = {"/create", "/update/{userId}"})
    public String editObject(Model model, @PathVariable(name = "userId") Optional<Long> userId) {
        User user = null;
        if (userId.isPresent()) {
            user = userService.getById(userId.get());
        } else {
            user = new User();
        }
        model.addAttribute("user", user);

        return "users/editUser";
    }

    /**
     * Deletes the object with <code>id</code>.
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/delete/{userId}")
    @Override
    public String delete(Model model, @PathVariable(name = "userId") Long id) {
//        validate(id);
        userService.delete(id);
        return "redirect:/users/list";
    }

}
