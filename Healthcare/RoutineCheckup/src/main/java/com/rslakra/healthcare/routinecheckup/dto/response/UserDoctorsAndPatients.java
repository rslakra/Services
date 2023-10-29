package com.rslakra.healthcare.routinecheckup.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:10 PM
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDoctorsAndPatients {

    private UserResponseDto user;

    private String userPicUrl;

    private List<DoctorResponseDto> doctors;

    private List<PatientResponseDto> patients;

}
