package com.rslakra.healthcare.routinecheckup.service.impl;

import com.rslakra.healthcare.routinecheckup.dto.UserRequestDto;
import com.rslakra.healthcare.routinecheckup.dto.request.ProfilePicRequestDto;
import com.rslakra.healthcare.routinecheckup.dto.response.DoctorResponseDto;
import com.rslakra.healthcare.routinecheckup.dto.response.PatientResponseDto;
import com.rslakra.healthcare.routinecheckup.dto.response.UserDoctorsAndPatients;
import com.rslakra.healthcare.routinecheckup.dto.response.UserResponseDto;
import com.rslakra.healthcare.routinecheckup.dto.response.UsersDoctorsAndPatients;
import com.rslakra.healthcare.routinecheckup.entity.RoleEntity;
import com.rslakra.healthcare.routinecheckup.entity.UserEntity;
import com.rslakra.healthcare.routinecheckup.repository.UserRepository;
import com.rslakra.healthcare.routinecheckup.service.ReCaptchaApproveComponent;
import com.rslakra.healthcare.routinecheckup.service.RoleService;
import com.rslakra.healthcare.routinecheckup.service.UserRegistrationAttemptsService;
import com.rslakra.healthcare.routinecheckup.service.UserService;
import com.rslakra.healthcare.routinecheckup.service.mail.RegistrationNotificationService;
import com.rslakra.healthcare.routinecheckup.service.security.TokenComponent;
import com.rslakra.healthcare.routinecheckup.utils.components.DtoUtils;
import com.rslakra.healthcare.routinecheckup.utils.components.holder.FileStorageConstants;
import com.rslakra.healthcare.routinecheckup.utils.components.holder.Messages;
import com.rslakra.healthcare.routinecheckup.utils.exceptions.IncorrectUrlException;
import com.rslakra.healthcare.routinecheckup.utils.exceptions.UserMismatchException;
import com.rslakra.healthcare.routinecheckup.utils.exceptions.UserNotFoundException;
import com.rslakra.healthcare.routinecheckup.utils.exceptions.UserValidationException;
import com.rslakra.healthcare.routinecheckup.utils.security.RoleNames;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.owasp.encoder.Encode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:20 PM
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final Messages messages;

    private final DtoUtils dtoUtils;

    private final PasswordEncoder passwordEncoder;

    private final ReCaptchaApproveComponent reCaptchaApproveComponent;

    private final RoleService roleService;

    private final UserRegistrationAttemptsService
        userRegistrationAttemptsService;

    private final TokenComponent tokenComponent;

    private final FileStorageConstants fileStorageConstants;

    private final RegistrationNotificationService
        registrationNotificationService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(
        @NonNull String username
    ) throws UsernameNotFoundException {
        Optional<UserEntity> userOptional
            = userRepository.findByLogin(username);
        UserEntity user = userOptional.orElseThrow(
            () -> new UsernameNotFoundException(username)
        );
        if (user.getIsTemporary()) {
            throw new UsernameNotFoundException(username);
        }

        UserDetails result = dtoUtils.getUserDetails(user);

        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUserByLogin(@NonNull String login) {
        UserEntity user = findByLogin(login);

        UserResponseDto result = dtoUtils.convertUser(user);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity getUserEntityByLogin(String login) {
        UserEntity result = findByLogin(login);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUserById(@NonNull String id) {
        UserEntity user = findById(id);

        UserResponseDto result = dtoUtils.convertUser(user);
        return result;
    }

    @Override
    @Transactional
    public UserResponseDto registerNewUser(
        @NonNull UserRequestDto user,
        String captchaResponse,
        RoleNames roleName,
        String userIp
    ) {
        UserRequestDto sanitizedUser = dtoUtils.sanitizeUser(user);
        boolean extraLastRegistration
            = userRegistrationAttemptsService.isExtraLastRegistration(
            userIp
        );
        if (extraLastRegistration) {
            reCaptchaApproveComponent.approve(captchaResponse);
        }

        UserEntity userEntity = dtoUtils.convertUser(sanitizedUser);
        String encodedPassword
            = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(encodedPassword);

        Optional<RoleEntity> roleOpt
            = roleService.findByName(roleName.getValue());
        RoleEntity role = roleOpt.orElseThrow(() -> {
            String message
                = String.format(
                "A role named \"%s\" does not exist",
                roleName.getValue()
            );
            return new RuntimeException(message);
        });
        userEntity.setRole(role);

        userEntity.setIsTemporary(true);

        if (
            userRepository.existsByLogin(userEntity.getLogin()) ||
            userRepository.existsByMail(userEntity.getMail())
        ) {
            UserResponseDto result = dtoUtils.convertUser(userEntity);
            return result;
        }

        UserEntity saved = userRepository.save(userEntity);
        registrationNotificationService.sendRegistrationEmail(saved);
        UserResponseDto result = dtoUtils.convertUser(saved);

        return result;
    }

    @Override
    @Transactional
    public UserResponseDto updateUser(
        @NonNull UserRequestDto user,
        @NonNull String login
    ) {
        UserRequestDto sanitizedUser = dtoUtils.sanitizeUser(user);
        UserEntity old = findByLogin(login);
        validateUpdateUser(sanitizedUser, old);
        UserEntity userEntity = dtoUtils.convertUser(sanitizedUser);

        userEntity.setLogin(login);
        userEntity.setPassword(old.getPassword());
        userEntity.setMail(old.getMail());
        userEntity.setCreationTime(old.getCreationTime());
        userEntity.setProfilePicUrl(old.getProfilePicUrl());
        UserEntity toUpdate = dtoUtils.merge(old, userEntity);

        UserEntity updated = userRepository.save(toUpdate);
        UserResponseDto result = dtoUtils.convertUser(updated);

        return result;
    }

    @Override
    @Transactional
    public UserResponseDto deleteUserById(@NonNull String id) {
        UserEntity user = findById(id);
        userRepository.delete(user);

        UserResponseDto result = dtoUtils.convertUser(user);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public UsersDoctorsAndPatients getAllUsersDoctorsAndPatients() {
        Iterable<UserEntity> all = userRepository.findAll();
        List<UserDoctorsAndPatients> doctorsAndPatients
            = StreamSupport.stream(all.spliterator(), false)
            .map(this::getUserDoctorsAndPatients)
            .collect(Collectors.toList());

        UsersDoctorsAndPatients result
            = new UsersDoctorsAndPatients(doctorsAndPatients);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDoctorsAndPatients getUserDoctorsAndPatientsByLogin(
        @NonNull String login
    ) {
        UserEntity user = findByLogin(login);
        UserDoctorsAndPatients userDoctorsAndPatients
            = getUserDoctorsAndPatients(user);
        return userDoctorsAndPatients;
    }

    @Override
    @Transactional
    public void completeRegistration(String registrationToken) {
        if (registrationToken == null) {
            return;
        }

        String loginFromToken
            = tokenComponent.getLoginFromRegistrationToken(
            registrationToken
        );
        UserEntity user = findByLogin(loginFromToken);

        if (user.getIsTemporary()) {
            user.setIsTemporary(false);
            userRepository.save(user);
        }
    }

    @Override
    public String changeProfilePic(ProfilePicRequestDto dto, String login) {
        String urlString = dto.getUrl();
        URL url;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            throw new IncorrectUrlException(messages.getIncorrectUrl());
        }

        String host = url.getHost();
        String[] allowList
            = fileStorageConstants.getExternalPicUrlsAllowList();
        boolean hostInAllowList = Arrays.asList(allowList).contains(host);
        if (!hostInAllowList) {
            throw new IncorrectUrlException(
                messages.getUrlUnverifiedSource()
            );
        }

        UserEntity user = findByLogin(login);
        String sanitizedUrl = Encode.forHtml(urlString);
        user.setProfilePicUrl(sanitizedUrl);
        userRepository.save(user);

        return sanitizedUrl;
    }

    private UserDoctorsAndPatients getUserDoctorsAndPatients(
        @NonNull UserEntity user
    ) {
        List<DoctorResponseDto> doctors = user.getUserDoctors().stream()
            .map(dtoUtils::convertDoctor)
            .collect(Collectors.toList());
        List<PatientResponseDto> patients = user.getUserPatients().stream()
            .map(dtoUtils::convertPatient)
            .collect(Collectors.toList());
        UserResponseDto userResponseDto = dtoUtils.convertUser(user);

        UserDoctorsAndPatients result = new UserDoctorsAndPatients(
            userResponseDto,
            user.getProfilePicUrl(),
            doctors,
            patients
        );
        return result;
    }

    private UserEntity findById(String id) {
        Optional<UserEntity> userOptional
            = userRepository.findById(UUID.fromString(id));
        UserEntity user = userOptional.orElseThrow(() -> {
            String userNotFoundTemplate
                = messages.getUserNotFoundByIdTemplate();
            String message = String.format(userNotFoundTemplate, id);
            return new UserNotFoundException(message);
        });

        return user;
    }

    private UserEntity findByLogin(String login) {
        Optional<UserEntity> userOptional
            = userRepository.findByLogin(login);
        UserEntity user = userOptional.orElseThrow(() -> {
            String userNotFoundTemplate
                = messages.getUserNotFoundByLoginTemplate();
            String message = String.format(userNotFoundTemplate, login);
            return new UserNotFoundException(message);
        });

        return user;
    }

    private void validateUpdateUser(
        UserRequestDto newUser,
        UserEntity oldUser
    ) {
        if (!oldUser.getId().toString().equals(newUser.getId())) {
            String userHaveNotAccess = messages.getUserHaveNotAccess();
            throw new UserMismatchException(userHaveNotAccess);
        }
        if (!oldUser.getLogin().equals(newUser.getLogin())) {
            String message
                = messages.getUserValidationLoginForbiddenChange();
            throw new UserValidationException(message);
        }
    }

}
