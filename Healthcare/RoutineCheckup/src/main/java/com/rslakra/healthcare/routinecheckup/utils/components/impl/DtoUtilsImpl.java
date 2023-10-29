package com.rslakra.healthcare.routinecheckup.utils.components.impl;

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
import com.rslakra.healthcare.routinecheckup.utils.components.DtoUtils;
import com.rslakra.healthcare.routinecheckup.utils.mappers.DoctorDtoEntityMapper;
import com.rslakra.healthcare.routinecheckup.utils.mappers.PatientDtoToEntityMapper;
import com.rslakra.healthcare.routinecheckup.utils.mappers.ServiceScheduleDtoEntityMapper;
import com.rslakra.healthcare.routinecheckup.utils.mappers.UserDtoEntityMapper;
import lombok.RequiredArgsConstructor;
import org.owasp.encoder.Encode;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DtoUtilsImpl implements DtoUtils {

    private final DoctorDtoEntityMapper doctorDtoEntityMapper;
    private final UserDtoEntityMapper userDtoEntityMapper;
    private final PatientDtoToEntityMapper patientDtoToEntityMapper;
    private final ServiceScheduleDtoEntityMapper serviceScheduleDtoEntityMapper;

    /**
     * @param userEntity
     * @return
     */
    @Override
    public UserDetails getUserDetails(UserEntity userEntity) {
        UserDetails userDetails = User.builder()
            .username(userEntity.getLogin())
            .password(userEntity.getPassword())
            .roles(userEntity.getRole().getRoleName())
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .disabled(false)
            .build();

        return userDetails;
    }

    /**
     * @param userEntity
     * @return
     */
    @Override
    @Transactional
    public UserResponseDto convertUser(UserEntity userEntity) {
        List<DoctorEntity> doctors = userEntity.getUserDoctors();
        doctors = doctors == null ? new ArrayList<>() : doctors;

        List<PatientEntity> patients = userEntity.getUserPatients();
        patients = patients == null ?
                   new ArrayList<>()
                                    : userEntity.getUserPatients();

        UserResponseDto result
            = userDtoEntityMapper.userEntityToUserResponseDto(userEntity);
        result.setDoctorsIds(
            doctors.stream()
                .map(doc -> doc.getId().toString())
                .collect(Collectors.toList())
        );
        result.setPatientsIds(
            patients.stream()
                .map(pat -> pat.getId().toString())
                .collect(Collectors.toList())
        );

        return result;
    }

    @Override
    public UserEntity convertUser(UserRequestDto userRequestDto) {
        return userDtoEntityMapper.userRequestDtoToUserEntity(userRequestDto);
    }

    @Override
    public UserRequestDto sanitizeUser(UserRequestDto dto) {
        String firstName = dto.getFirstName() == null
                           ? null
                           : Encode.forHtml(dto.getFirstName());
        String lastName = dto.getLastName() == null
                          ? null
                          : Encode.forHtml(dto.getLastName());
        dto.setFirstName(firstName);
        dto.setLastName(lastName);

        return dto;
    }

    @Override
    public UserEntity merge(UserEntity from, UserEntity to) {
        if (to.getPassword() == null) {
            to.setPassword(from.getPassword());
        }
        if (to.getFirstName() == null) {
            to.setFirstName(from.getFirstName());
        }
        if (to.getLastName() == null) {
            to.setFirstName(from.getFirstName());
        }
        if (to.getUserPatients() == null) {
            to.setUserPatients(from.getUserPatients());
        }
        if (to.getUserDoctors() == null) {
            to.setUserDoctors(from.getUserDoctors());
        }
        if (to.getIsTemporary() == null) {
            to.setIsTemporary(from.getIsTemporary());
        }
        if (to.getMail() == null) {
            to.setMail(from.getMail());
        }
        if (to.getProfilePicUrl() == null) {
            to.setProfilePicUrl(from.getProfilePicUrl());
        }

        return to;
    }

    @Override
    public DoctorResponseDto convertDoctor(DoctorEntity doctorEntity) {
        return doctorDtoEntityMapper.doctorEntityToDoctorResponse(doctorEntity);
    }

    @Override
    public DoctorEntity convertDoctor(DoctorRequestDto doctorRequestDto) {
        return doctorDtoEntityMapper.doctorRequestDtoToDoctorEntity(doctorRequestDto);
    }

    @Override
    public DoctorRequestDto sanitizeDoctor(DoctorRequestDto dto) {
        String speciality = dto.getSpeciality() == null ? null : Encode.forHtml(dto.getSpeciality());
        dto.setSpeciality(speciality);
        return dto;
    }

    @Override
    public DoctorEntity merge(DoctorEntity from, DoctorEntity to) {
        if (to.getSpeciality() == null) {
            to.setSpeciality(from.getSpeciality());
        }
        if (to.getUserEntity() == null) {
            to.setUserEntity(from.getUserEntity());
        }

        return to;
    }

    @Override
    @Transactional
    public PatientResponseDto convertPatient(PatientEntity patientEntity) {
        PatientResponseDto result = patientDtoToEntityMapper.patientEntityToPatientResponseDto(patientEntity);
        result.setDoctor(convertDoctor(patientEntity.getDoctor()));

        return result;
    }

    @Override
    public PatientEntity convertPatient(PatientRequestDto patientRequestDto) {
        return patientDtoToEntityMapper.doctorRequestDtoToDoctorEntity(patientRequestDto);
    }

    @Override
    public PatientRequestDto sanitizePatient(PatientRequestDto dto) {
        String disease = dto.getDisease() == null ? null : Encode.forHtml(dto.getDisease());
        dto.setDisease(disease);
        return dto;
    }

    @Override
    public PatientEntity merge(PatientEntity from, PatientEntity to) {
        if (to.getDoctor() == null) {
            to.setDoctor(from.getDoctor());
        }
        if (to.getUserEntity() == null) {
            to.setUserEntity(from.getUserEntity());
        }
        if (to.getDisease() == null) {
            to.setDisease(from.getDisease());
        }
        if (to.getDiseaseOnsetTime() == null) {
            to.setDiseaseOnsetTime(from.getDiseaseOnsetTime());
        }
        if (to.getEndTimeOfIllness() == null) {
            to.setEndTimeOfIllness(from.getEndTimeOfIllness());
        }

        return to;
    }

    @Override
    public ServiceDataDto convertService(ServiceScheduleEntity entity) {
        return serviceScheduleDtoEntityMapper.serviceEntityToDto(entity);
    }
}
