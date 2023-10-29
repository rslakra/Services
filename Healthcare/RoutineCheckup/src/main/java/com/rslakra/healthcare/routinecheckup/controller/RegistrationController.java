package com.rslakra.healthcare.routinecheckup.controller;

import com.rslakra.healthcare.routinecheckup.dto.UserRequestDto;
import com.rslakra.healthcare.routinecheckup.service.UserRegistrationAttemptsService;
import com.rslakra.healthcare.routinecheckup.service.UserService;
import com.rslakra.healthcare.routinecheckup.utils.components.holder.CaptchaConstants;
import com.rslakra.healthcare.routinecheckup.utils.constants.ModelAttributesNames;
import com.rslakra.healthcare.routinecheckup.utils.constants.ViewNames;
import com.rslakra.healthcare.routinecheckup.utils.security.RoleNames;
import com.rslakra.healthcare.routinecheckup.utils.validation.constraint.group.user.CreateUserValidationGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:00 PM
 */
@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final CaptchaConstants captchaConstants;

    private final UserService userService;

    private final UserRegistrationAttemptsService
        userRegistrationAttemptsService;

    private final String CAPTCHA_RESPONSE_PARAM_NAME = "g-recaptcha-response";

    @GetMapping(value = ViewNames.REGISTRATION_URL)
    public String registrationView(
        Model model,
        HttpServletRequest request,
        Principal principal
    ) {
        if (principal != null) {
            return "redirect:" + ViewNames.DOCTORS_AND_PATIENTS_LIST_URL;
        }

        model.addAttribute(
            ModelAttributesNames.REGISTRATION_DTO,
            new UserRequestDto()
        );

        model.addAttribute(
            ModelAttributesNames.CAPTCHA_SITE_KEY,
            captchaConstants.getSiteKey()
        );

        String userIp = request.getRemoteAddr();
        boolean extraCurrentRegistration
            = userRegistrationAttemptsService
            .isExtraCurrentRegistration(userIp);
        model.addAttribute(
            ModelAttributesNames.IS_EXTRA_REGISTRATION,
            extraCurrentRegistration
        );

        return ViewNames.REGISTRATION_VIEW_NAME;
    }

    @GetMapping(value = ViewNames.REGISTRATION_URL + "/{registration_token}")
    public String completeRegistration(
        @PathVariable("registration_token") String registrationToken
    ) {
        userService.completeRegistration(registrationToken);

        return "redirect:" + ViewNames.LOGIN_URL;
    }

    @PostMapping(value = ViewNames.REGISTRATION_URL)
    public String registration(
        @ModelAttribute(ModelAttributesNames.REGISTRATION_DTO)
        @Validated(CreateUserValidationGroup.class)
            UserRequestDto userRequestDto,
        @RequestParam(
            value = CAPTCHA_RESPONSE_PARAM_NAME,
            required = false
        )
            String captchaResponse,
        HttpServletRequest request
    ) {
        String userIp = request.getRemoteAddr();
        userService.registerNewUser(
            userRequestDto,
            captchaResponse,
            RoleNames.USER,
            userIp
        );

        return ViewNames.PRE_COMPLETE_REGISTRATION_VIEW_NAME;
    }

}
