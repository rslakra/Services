package com.rslakra.healthcare.routinecheckup.controller;


import com.rslakra.healthcare.routinecheckup.dto.request.LoginDto;
import com.rslakra.healthcare.routinecheckup.utils.constants.ModelAttributesNames;
import com.rslakra.healthcare.routinecheckup.utils.constants.ViewNames;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 3:59 PM
 */
@Controller
@RequestMapping(ViewNames.LOGIN_URL)
@RequiredArgsConstructor
public class LoginController {

    @GetMapping
    public String login(
        @RequestParam(value = "error", required = false) String error,
        @RequestParam(value = "logout", required = false) String logout,
        Model model
    ) {
        String loginMessage = null;
        if (error != null) {
            loginMessage = "Incorrect credentials!";
        }
        if (logout != null) {
            loginMessage = "You have successfully logged out";
        }
        model.addAttribute(
            ModelAttributesNames.LOGIN_MESSAGE_NAME,
            loginMessage
        );
        model.addAttribute(new LoginDto());

        return ViewNames.LOGIN_VIEW_NAME;
    }

}
