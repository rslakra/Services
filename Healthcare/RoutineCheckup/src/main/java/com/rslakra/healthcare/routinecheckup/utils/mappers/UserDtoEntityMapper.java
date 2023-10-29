package com.rslakra.healthcare.routinecheckup.utils.mappers;

import com.rslakra.healthcare.routinecheckup.dto.UserRequestDto;
import com.rslakra.healthcare.routinecheckup.dto.response.UserResponseDto;
import com.rslakra.healthcare.routinecheckup.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface UserDtoEntityMapper {

    @Mappings({
        @Mapping(
            target = "id",
            expression = "java( dto.getId() == null" +
                         " ? null" +
                         " : UUID.fromString(dto.getId()) )"
        ),
        @Mapping(target = "login", source = "dto.login"),
        @Mapping(target = "password", source = "password"),
        @Mapping(target = "firstName", source = "dto.firstName"),
        @Mapping(target = "lastName", source = "dto.lastName"),
        @Mapping(target = "mail", source = "dto.mail")
    })
    UserEntity userRequestDtoToUserEntity(UserRequestDto dto);

    @Mappings({
        @Mapping(
            target = "id",
            expression = "java( entity.getId() == null" +
                         " ? null" +
                         " : entity.getId().toString() )"
        ),
        @Mapping(target = "login", source = "entity.login"),
        @Mapping(target = "firstName", source = "entity.firstName"),
        @Mapping(target = "lastName", source = "entity.lastName"),
        @Mapping(target = "doctorsIds", ignore = true),
        @Mapping(target = "patientsIds", ignore = true)
    })
    UserResponseDto userEntityToUserResponseDto(UserEntity entity);

}
