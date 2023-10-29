package com.rslakra.healthcare.routinecheckup.service;

import com.rslakra.healthcare.routinecheckup.dto.request.DoctorRequestDto;
import com.rslakra.healthcare.routinecheckup.dto.response.DoctorResponseDto;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:14 PM
 */
public interface DoctorService {

    DoctorResponseDto findDoctorById(String id);

    DoctorResponseDto saveDoctor(
        DoctorRequestDto doctor,
        String currentUserLogin
    );

    DoctorResponseDto updateDoctor(
        DoctorRequestDto doctor,
        String currentUserLogin
    );

    List<DoctorResponseDto> getAllDoctors();

    void validateDoctorBelongsToUser(String doctorId, String userLogin);

    List<DoctorResponseDto> searchDoctor(String searchString);

}
