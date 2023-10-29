package com.rslakra.healthcare.routinecheckup.controller;


import com.rslakra.healthcare.routinecheckup.dto.request.DoctorRequestDto;
import com.rslakra.healthcare.routinecheckup.dto.response.DoctorResponseDto;
import com.rslakra.healthcare.routinecheckup.dto.response.FoundedDoctorsDto;
import com.rslakra.healthcare.routinecheckup.service.DoctorService;
import com.rslakra.healthcare.routinecheckup.utils.constants.ModelAttributesNames;
import com.rslakra.healthcare.routinecheckup.utils.constants.Patterns;
import com.rslakra.healthcare.routinecheckup.utils.constants.ViewNames;
import com.rslakra.healthcare.routinecheckup.utils.validation.constraint.group.doctor.UpdateDoctorValidationGroup;
import lombok.RequiredArgsConstructor;
import org.owasp.encoder.Encode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

import javax.validation.constraints.Pattern;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 3:57 PM
 */
@Controller
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping(ViewNames.UPDATE_DOCTOR_URL)
    public String updateDoctorView(
        @RequestParam("id")
        @Pattern(regexp = Patterns.UUID_PATTERN)
            String doctorId,
        Model model
    ) {
        DoctorResponseDto doctor = doctorService.findDoctorById(
            doctorId
        );

        model.addAttribute(ModelAttributesNames.DOCTOR_OBJECT_NAME, doctor);

        return ViewNames.UPDATE_DOCTOR_VIEW_NAME;
    }

    @PutMapping(ViewNames.UPDATE_DOCTOR_URL)
    @ResponseBody
    public DoctorResponseDto updateDoctor(
        @RequestBody
        @Validated(UpdateDoctorValidationGroup.class)
        DoctorRequestDto doctorRequestDto,
        Principal principal
    ) {
        String login = principal.getName();
        DoctorResponseDto result = doctorService.updateDoctor(
            doctorRequestDto,
            login
        );

        return result;
    }

    @GetMapping(ViewNames.SEARCH_DOCTOR_URL)
    public String searchDoctors(
        @RequestParam(value = "searchStr", required = false) String search,
        Model model
    ) {
        FoundedDoctorsDto dto = new FoundedDoctorsDto();

        if (StringUtils.hasText(search)) {
            dto.setDoctors(doctorService.searchDoctor(search.trim()));
            String sanitizedSearchString = Encode.forHtml(search);
            dto.setSearchString(sanitizedSearchString);
        } else {
            dto.setDoctors(doctorService.getAllDoctors());
        }

        model.addAttribute(ModelAttributesNames.FOUNDED_DOCTORS, dto);

        return ViewNames.SEARCH_DOCTOR_VIEW_NAME;
    }

}
