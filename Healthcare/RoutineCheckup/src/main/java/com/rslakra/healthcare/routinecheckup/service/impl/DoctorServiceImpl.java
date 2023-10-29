package com.rslakra.healthcare.routinecheckup.service.impl;

import com.rslakra.healthcare.routinecheckup.dto.request.DoctorRequestDto;
import com.rslakra.healthcare.routinecheckup.dto.response.DoctorResponseDto;
import com.rslakra.healthcare.routinecheckup.entity.DoctorEntity;
import com.rslakra.healthcare.routinecheckup.entity.UserEntity;
import com.rslakra.healthcare.routinecheckup.repository.DoctorRepository;
import com.rslakra.healthcare.routinecheckup.service.DoctorService;
import com.rslakra.healthcare.routinecheckup.service.UserService;
import com.rslakra.healthcare.routinecheckup.utils.components.DtoUtils;
import com.rslakra.healthcare.routinecheckup.utils.components.holder.Messages;
import com.rslakra.healthcare.routinecheckup.utils.exceptions.UserMismatchException;
import com.rslakra.healthcare.routinecheckup.utils.exceptions.UserNotFoundException;
import com.rslakra.healthcare.routinecheckup.utils.security.RoleNames;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.constraints.NotNull;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:18 PM
 */
@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    private final Messages messages;

    private final DtoUtils dtoUtils;

    private final UserService userService;

    @Override
    @Transactional(readOnly = true)
    public DoctorResponseDto findDoctorById(@NotNull String id) {
        DoctorEntity doctor = findById(UUID.fromString(id));

        DoctorResponseDto result = dtoUtils.convertDoctor(doctor);
        return result;
    }

    @Override
    @Transactional
    public DoctorResponseDto saveDoctor(
        DoctorRequestDto doctor,
        String currentUserLogin
    ) {
        DoctorRequestDto sanitizedDoctor
            = dtoUtils.sanitizeDoctor(doctor);
        DoctorEntity doctorEntity
            = dtoUtils.convertDoctor(sanitizedDoctor);
        validateDoctorBelongsToUser(doctorEntity, currentUserLogin);

        DoctorEntity saved = doctorRepository.save(doctorEntity);

        DoctorResponseDto result = dtoUtils.convertDoctor(saved);
        return result;
    }

    @Override
    @Transactional
    public DoctorResponseDto updateDoctor(
        DoctorRequestDto doctor,
        String currentUserLogin
    ) {
        DoctorRequestDto sanitizedDoctor
            = dtoUtils.sanitizeDoctor(doctor);
        DoctorEntity old
            = findById(UUID.fromString(sanitizedDoctor.getId()));
        validateDoctorBelongsToUser(old, currentUserLogin);

        DoctorEntity doctorEntity
            = dtoUtils.convertDoctor(sanitizedDoctor);
        DoctorEntity toUpdate = dtoUtils.merge(old, doctorEntity);
        doctorEntity.getUserEntity().setId(old.getUserEntity().getId());

        DoctorEntity updated = doctorRepository.save(toUpdate);

        DoctorResponseDto result = dtoUtils.convertDoctor(updated);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DoctorResponseDto> getAllDoctors() {
        Iterable<DoctorEntity> allDoctors = doctorRepository.findAll();
        List<DoctorResponseDto> result
            = StreamSupport.stream(allDoctors.spliterator(), false)
            .map(dtoUtils::convertDoctor)
            .collect(Collectors.toList());

        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public void validateDoctorBelongsToUser(
        String doctorId,
        String userLogin
    ) {
        DoctorEntity doctorEntity = findById(UUID.fromString(doctorId));
        validateDoctorBelongsToUser(doctorEntity, userLogin);
    }

    @Override
    public List<DoctorResponseDto> searchDoctor(String searchString) {
        List<DoctorEntity> allBySearchString
            = doctorRepository.findAllBySearchString(searchString);

        List<DoctorResponseDto> result = allBySearchString.stream()
            .map(dtoUtils::convertDoctor)
            .collect(Collectors.toList());
        return result;
    }

    private DoctorEntity findById(UUID id) {
        Optional<DoctorEntity> doctorOptional
            = doctorRepository.findById(id);
        DoctorEntity result = doctorOptional.orElseThrow(() -> {
            String message = messages.getDoctorHaveNotAccess();
            return new UserNotFoundException(message);
        });

        return result;
    }

    private void validateDoctorBelongsToUser(
        DoctorEntity doctorEntity,
        String userLogin
    ) {
        UserEntity userByLogin
            = userService.getUserEntityByLogin(userLogin);
        if (RoleNames.ADMIN.getValue().equals(
            userByLogin.getRole().getRoleName()
        )) {
            return;
        }

        String userId = userByLogin.getId().toString();
        String doctorUserId = doctorEntity.getUserEntity().getId().toString();
        if (!userId.equals(doctorUserId)) {
            String message = messages.getDoctorHaveNotAccess();
            throw new UserMismatchException(message);
        }
    }

}
