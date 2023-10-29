package com.rslakra.healthcare.routinecheckup.utils.mappers;

import com.rslakra.healthcare.routinecheckup.dto.request.PatientRequestDto;
import com.rslakra.healthcare.routinecheckup.dto.response.PatientResponseDto;
import com.rslakra.healthcare.routinecheckup.entity.PatientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface PatientDtoToEntityMapper {

    @Mappings({
        @Mapping(
            target = "id",
            expression = "java( dto.getId() == null " +
                         "? null " +
                         ": UUID.fromString(dto.getId()) )"
        ),
        @Mapping(
            target = "disease",
            source = "dto.disease"
        ),
        @Mapping(
            target = "diseaseOnsetTime",
            source = "dto.diseaseOnsetTime"
        ),
        @Mapping(
            target = "endTimeOfIllness",
            source = "dto.endTimeOfIllness"
        ),
        @Mapping(
            target = "userEntity.id",
            expression = "java( patientRequestDto.getUserId()" +
                         " == null ? null : " +
                         "UUID.fromString(patientRequestDto.getUserId()) )"
        ),
        @Mapping(
            target = "doctor.id",
            expression
                = "java( patientRequestDto.getDoctorId() == null" +
                  " ? null" +
                  " : UUID.fromString(" +
                  "patientRequestDto.getDoctorId()) )"
        )
    })
    PatientEntity doctorRequestDtoToDoctorEntity(PatientRequestDto dto);

    @Mappings({
        @Mapping(
            target = "id",
            expression = "java( entity.getId() == null " +
                         "? null " +
                         ": entity.getId().toString() )"
        ),
        @Mapping(
            target = "disease",
            source = "entity.disease"
        ),
        @Mapping(
            target = "diseaseOnsetTime",
            source = "entity.diseaseOnsetTime"
        ),
        @Mapping(
            target = "endTimeOfIllness",
            source = "entity.endTimeOfIllness"
        ),
        @Mapping(
            target = "firstName",
            source = "entity.userEntity.firstName"
        ),
        @Mapping(
            target = "lastName",
            source = "entity.userEntity.lastName"
        ),
        @Mapping(
            target = "doctor.id",
            ignore = true
        )
    })
    PatientResponseDto patientEntityToPatientResponseDto(PatientEntity entity);

}
