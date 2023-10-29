package com.rslakra.healthcare.routinecheckup.service;


import com.rslakra.healthcare.routinecheckup.dto.UserRequestDto;
import com.rslakra.healthcare.routinecheckup.dto.request.ProfilePicRequestDto;
import com.rslakra.healthcare.routinecheckup.dto.response.UserDoctorsAndPatients;
import com.rslakra.healthcare.routinecheckup.dto.response.UserResponseDto;
import com.rslakra.healthcare.routinecheckup.dto.response.UsersDoctorsAndPatients;
import com.rslakra.healthcare.routinecheckup.entity.UserEntity;
import com.rslakra.healthcare.routinecheckup.utils.security.RoleNames;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:16 PM
 */
public interface UserService extends UserDetailsService {

    UserResponseDto getUserByLogin(String login);

    UserEntity getUserEntityByLogin(String login);

    UserResponseDto getUserById(String id);

    UserResponseDto registerNewUser(
        UserRequestDto user,
        String captchaResponse,
        RoleNames roleName,
        String userIp
    );

    UserResponseDto updateUser(UserRequestDto user, String login);

    UserResponseDto deleteUserById(String id);

    UserDoctorsAndPatients getUserDoctorsAndPatientsByLogin(String login);

    UsersDoctorsAndPatients getAllUsersDoctorsAndPatients();

    void completeRegistration(String registrationToken);

    String changeProfilePic(ProfilePicRequestDto dto, String login);

}
