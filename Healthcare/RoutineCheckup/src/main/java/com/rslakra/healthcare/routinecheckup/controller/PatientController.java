package com.rslakra.healthcare.routinecheckup.controller;

import com.rslakra.healthcare.routinecheckup.dto.request.PatientRequestDto;
import com.rslakra.healthcare.routinecheckup.dto.response.DoctorResponseDto;
import com.rslakra.healthcare.routinecheckup.dto.response.PatientResponseDto;
import com.rslakra.healthcare.routinecheckup.service.DoctorService;
import com.rslakra.healthcare.routinecheckup.service.PatientService;
import com.rslakra.healthcare.routinecheckup.utils.constants.ModelAttributesNames;
import com.rslakra.healthcare.routinecheckup.utils.constants.Patterns;
import com.rslakra.healthcare.routinecheckup.utils.constants.ViewNames;
import com.rslakra.healthcare.routinecheckup.utils.validation.constraint.group.patient.UpdatePatientValidationGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

import javax.validation.constraints.Pattern;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 3:59 PM
 */
@Controller
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    private final DoctorService doctorService;

    @GetMapping(value = ViewNames.UPDATE_PATIENT_URL)
    public String updatePatientView(
        @RequestParam("id")
        @Pattern(regexp = Patterns.UUID_PATTERN)
            String patientId,
        Principal principal,
        Model model
    ) {
        String login = principal.getName();
        PatientResponseDto patient
            = patientService.getPatientById(patientId, login);
        List<DoctorResponseDto> allDoctors
            = doctorService.getAllDoctors();

        model.addAttribute(ModelAttributesNames.PATIENT_OBJECT_NAME, patient);
        model.addAttribute(
            ModelAttributesNames.DOCTORS_OBJECT_NAME,
            allDoctors
        );

        return ViewNames.UPDATE_PATIENT_VIEW_NAME;
    }

    @PutMapping(value = ViewNames.UPDATE_PATIENT_URL)
    @ResponseBody
    public PatientResponseDto updatePatient(
        @RequestBody
        @Validated(UpdatePatientValidationGroup.class)
        PatientRequestDto patientRequestDto,
        Principal principal
    ) {
        String login = principal.getName();
        PatientResponseDto result
            = patientService.updatePatient(patientRequestDto, login);

        return result;
    }


}
