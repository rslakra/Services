package com.rslakra.healthcare.routinecheckup.utils.components;

import com.rslakra.healthcare.routinecheckup.dto.ServiceDataDto;
import com.rslakra.healthcare.routinecheckup.dto.UserRequestDto;
import com.rslakra.healthcare.routinecheckup.dto.request.DoctorRequestDto;
import com.rslakra.healthcare.routinecheckup.dto.request.PatientRequestDto;
import com.rslakra.healthcare.routinecheckup.dto.response.DoctorResponseDto;
import com.rslakra.healthcare.routinecheckup.dto.response.PatientResponseDto;
import com.rslakra.healthcare.routinecheckup.dto.response.UserResponseDto;
import com.rslakra.healthcare.routinecheckup.entity.DoctorEntity;
import com.rslakra.healthcare.routinecheckup.entity.PatientEntity;
import com.rslakra.healthcare.routinecheckup.entity.ServiceScheduleEntity;
import com.rslakra.healthcare.routinecheckup.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:43 PM
 */
public interface DtoUtils {

    UserDetails getUserDetails(UserEntity userEntity);

    UserResponseDto convertUser(UserEntity userEntity);

    UserEntity convertUser(UserRequestDto userRequestDto);

    UserRequestDto sanitizeUser(UserRequestDto userRequestDto);

    UserEntity merge(UserEntity from, UserEntity to);

    DoctorResponseDto convertDoctor(DoctorEntity doctorEntity);

    DoctorEntity convertDoctor(DoctorRequestDto doctorRequestDto);

    DoctorRequestDto sanitizeDoctor(DoctorRequestDto doctorRequestDto);

    DoctorEntity merge(DoctorEntity from, DoctorEntity to);

    PatientResponseDto convertPatient(PatientEntity patientEntity);

    PatientEntity convertPatient(PatientRequestDto patientRequestDto);

    PatientRequestDto sanitizePatient(PatientRequestDto patientRequestDto);

    PatientEntity merge(PatientEntity from, PatientEntity to);

    ServiceDataDto convertService(ServiceScheduleEntity entity);

}

