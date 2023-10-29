package com.rslakra.healthcare.routinecheckup.utils.mappers;

import com.rslakra.healthcare.routinecheckup.dto.request.DoctorRequestDto;
import com.rslakra.healthcare.routinecheckup.dto.response.DoctorResponseDto;
import com.rslakra.healthcare.routinecheckup.entity.DoctorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface DoctorDtoEntityMapper {

    @Mappings({
        @Mapping(
            target = "id",
            expression = "java( doctorRequestDto.getId() == null " +
                         "? null " +
                         ": UUID.fromString(doctorRequestDto.getId()) )"
        ),
        @Mapping(target = "speciality", source = "doctorRequestDto.speciality"),
        @Mapping(
            target = "userEntity.id",
            expression = "java( doctorRequestDto.getUserId() == null" +
                         " ? null " +
                         ": UUID.fromString(doctorRequestDto.getUserId()) )"
        )
    })
    DoctorEntity doctorRequestDtoToDoctorEntity(DoctorRequestDto doctorRequestDto);

    @Mappings({
        @Mapping(target = "id", expression = "java( doctorEntity.getId() == null ? null : doctorEntity.getId().toString() )"),
        @Mapping(target = "speciality", source = "doctorEntity.speciality"),
        @Mapping(target = "firstName", source = "doctorEntity.userEntity.firstName"),
        @Mapping(target = "lastName", source = "doctorEntity.userEntity.lastName")
    })
    DoctorResponseDto doctorEntityToDoctorResponse(DoctorEntity doctorEntity);

}
