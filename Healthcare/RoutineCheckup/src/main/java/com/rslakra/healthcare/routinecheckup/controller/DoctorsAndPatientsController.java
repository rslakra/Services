package com.rslakra.healthcare.routinecheckup.controller;


import com.rslakra.healthcare.routinecheckup.dto.request.ProfilePicRequestDto;
import com.rslakra.healthcare.routinecheckup.dto.response.UserDoctorsAndPatients;
import com.rslakra.healthcare.routinecheckup.service.UserService;
import com.rslakra.healthcare.routinecheckup.utils.constants.ModelAttributesNames;
import com.rslakra.healthcare.routinecheckup.utils.constants.ViewNames;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 3:58 PM
 */
@Controller
@RequestMapping({ViewNames.DOCTORS_AND_PATIENTS_LIST_URL, "/"})
@RequiredArgsConstructor
public class DoctorsAndPatientsController {

    private final UserService userService;

    @GetMapping
    public String doctorsAndPatientsList(Model model, Principal principal) {
        String login = principal.getName();
        UserDoctorsAndPatients userAndPatients
            = userService.getUserDoctorsAndPatientsByLogin(login);

        model.addAttribute(
            ModelAttributesNames.USER_DOCTORS_AND_PATIENTS_NAME,
            userAndPatients
        );

        model.addAttribute(
            ModelAttributesNames.CHANGE_PROFILE_PIC_DTO,
            new ProfilePicRequestDto()
        );

        return ViewNames.DOCTORS_AND_PATIENTS_LIST_VIEW_NAME;
    }

}
