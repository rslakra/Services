package com.rslakra.springbootsamples.emailservice.controller.web;

import com.rslakra.springbootsamples.emailservice.domain.user.IdentityDO;
import com.rslakra.springbootsamples.emailservice.dto.LoggedInUserRequest;
import com.rslakra.springbootsamples.emailservice.dto.PasswordChangeRequest;
import com.rslakra.springbootsamples.emailservice.repository.IdentityRepository;
import com.rslakra.springbootsamples.emailservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 3:29 PM
 */
@Controller
@RequestMapping("/user/change-password")
public class ChangePasswordController {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangePasswordController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private IdentityRepository identityRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @ModelAttribute("passwordChangeForm")
    public PasswordChangeRequest passwordChange() {
        return new PasswordChangeRequest();
    }

    @GetMapping
    public String displayPasswordChange(Model model) {
        return "change-password";
    }

    @PostMapping
    @Transactional
    public String handelPasswordChange(@ModelAttribute("passwordChangeForm") @Validated PasswordChangeRequest form,
                                       BindingResult result) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LoggedInUserRequest loggedInUserRequest = (LoggedInUserRequest) auth.getPrincipal();
        IdentityDO user = identityRepository.findById(loggedInUserRequest.getUserObjectId());
        if (user == null) {
            return "redirect:/login?notLoggedIn";
        } else if (form.getNewPassword().isEmpty()) {
            return "change-password";
        } else if (!form.getNewPassword().equals(form.getConfirmPassword())) {
            result.rejectValue("confirmPassword", null,
                               "You must enter the same password twice in order to confirm it.");
            LOGGER.info("Confirmation password mismatch.");
            return "change-password";
        } else if (!passwordEncoder.matches(form.getCurrentPassword(), user.getPassword())) {
            result.rejectValue("currentPassword", null, "Current password is incorrect.");
            return "change-password";
        }

        String updatedPassword = passwordEncoder.encode(form.getConfirmPassword());
        userService.updatePassword(updatedPassword, user.getId());

        return "redirect:/home?changeSuccess";
    }
}
