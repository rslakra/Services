package com.rslakra.healthcare.routinecheckup.controller;


import com.rslakra.healthcare.routinecheckup.dto.UserRequestDto;
import com.rslakra.healthcare.routinecheckup.dto.request.ProfilePicRequestDto;
import com.rslakra.healthcare.routinecheckup.dto.response.UserResponseDto;
import com.rslakra.healthcare.routinecheckup.service.UserService;
import com.rslakra.healthcare.routinecheckup.utils.constants.ModelAttributesNames;
import com.rslakra.healthcare.routinecheckup.utils.constants.ViewNames;
import com.rslakra.healthcare.routinecheckup.utils.validation.constraint.group.doctor.UpdateDoctorValidationGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

import javax.validation.Valid;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:01 PM
 */
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(ViewNames.UPDATE_USER_URL)
    public String updateUserView(Model model, Principal principal) {
        String login = principal.getName();
        UserResponseDto user = userService.getUserByLogin(login);

        model.addAttribute(ModelAttributesNames.USER_OBJECT_NAME, user);

        return ViewNames.UPDATE_USER_VIEW_NAME;
    }

    @PutMapping(ViewNames.UPDATE_USER_URL)
    @ResponseBody
    public UserResponseDto updateUser(
        @RequestBody
        @Validated(UpdateDoctorValidationGroup.class)
            UserRequestDto userRequestDto,
        Principal principal
    ) {
        String login = principal.getName();
        UserResponseDto result
            = userService.updateUser(userRequestDto, login);

        return result;
    }

    @PostMapping(ViewNames.CHANGE_USER_PICTURE_URL)
    public String changeUserPic(
        @ModelAttribute(ModelAttributesNames.CHANGE_PROFILE_PIC_DTO)
        @Valid
        ProfilePicRequestDto dto,
        Principal principal
    ) {
        String login = principal.getName();
        userService.changeProfilePic(dto, login);

        return "redirect:" + ViewNames.DOCTORS_AND_PATIENTS_LIST_URL;
    }

}
