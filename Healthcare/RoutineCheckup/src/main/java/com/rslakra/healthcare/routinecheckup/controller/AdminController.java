package com.rslakra.healthcare.routinecheckup.controller;

import com.rslakra.healthcare.routinecheckup.dto.response.UsersDoctorsAndPatients;
import com.rslakra.healthcare.routinecheckup.service.UserService;
import com.rslakra.healthcare.routinecheckup.utils.constants.ModelAttributesNames;
import com.rslakra.healthcare.routinecheckup.utils.constants.ViewNames;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 3:57 PM
 */
@Controller
@RequestMapping(value = ViewNames.ADMIN_BASE_PATH)
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping
    public String adminView(Model model) {
        UsersDoctorsAndPatients allUsersDoctorsAndPatients
            = userService.getAllUsersDoctorsAndPatients();
        model.addAttribute(
            ModelAttributesNames.USERS_DOCTORS_AND_PATIENTS,
            allUsersDoctorsAndPatients
        );

        return ViewNames.ADMIN_VIEW_NAME;
    }

}
