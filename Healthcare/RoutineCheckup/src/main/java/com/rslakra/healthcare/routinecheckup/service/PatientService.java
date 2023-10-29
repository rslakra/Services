package com.rslakra.healthcare.routinecheckup.service;


import com.rslakra.healthcare.routinecheckup.dto.request.PatientRequestDto;
import com.rslakra.healthcare.routinecheckup.dto.response.PatientResponseDto;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:15 PM
 */
public interface PatientService {

    PatientResponseDto getPatientById(String id, String currentUserLogin);

    PatientResponseDto savePatient(
        PatientRequestDto patientRequestDto,
        String currentUserLogin
    );

    PatientResponseDto updatePatient(
        PatientRequestDto patientRequestDto,
        String currentUserLogin
    );

}
