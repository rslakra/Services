package com.rslakra.healthcare.routinecheckup.service.impl;

import com.rslakra.healthcare.routinecheckup.dto.request.PatientRequestDto;
import com.rslakra.healthcare.routinecheckup.dto.response.PatientResponseDto;
import com.rslakra.healthcare.routinecheckup.entity.PatientEntity;
import com.rslakra.healthcare.routinecheckup.entity.UserEntity;
import com.rslakra.healthcare.routinecheckup.repository.PatientRepository;
import com.rslakra.healthcare.routinecheckup.service.PatientService;
import com.rslakra.healthcare.routinecheckup.service.UserService;
import com.rslakra.healthcare.routinecheckup.utils.components.DtoUtils;
import com.rslakra.healthcare.routinecheckup.utils.components.holder.Messages;
import com.rslakra.healthcare.routinecheckup.utils.exceptions.UserMismatchException;
import com.rslakra.healthcare.routinecheckup.utils.exceptions.UserNotFoundException;
import com.rslakra.healthcare.routinecheckup.utils.security.RoleNames;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:19 PM
 */
@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    private final UserService userService;

    private final Messages messages;

    private final DtoUtils dtoUtils;

    @Override
    @Transactional(readOnly = true)
    public PatientResponseDto getPatientById(String id, String currentUserLogin) {
        PatientEntity patient = findById(UUID.fromString(id));

        PatientResponseDto result = dtoUtils.convertPatient(patient);
        return result;
    }

    @Override
    @Transactional
    public PatientResponseDto savePatient(
        PatientRequestDto patientRequestDto,
        String currentUserLogin
    ) {
        PatientRequestDto sanitizedPatient
            = dtoUtils.sanitizePatient(patientRequestDto);
        PatientEntity patientEntity
            = dtoUtils.convertPatient(sanitizedPatient);
        validatePatientBelongsToUser(patientEntity, currentUserLogin);

        PatientEntity saved = patientRepository.save(patientEntity);

        PatientResponseDto result = dtoUtils.convertPatient(saved);
        return result;
    }

    @Override
    @Transactional
    public PatientResponseDto updatePatient(
        PatientRequestDto patientRequestDto,
        String currentUserLogin
    ) {
        PatientRequestDto sanitizedPatient
            = dtoUtils.sanitizePatient(patientRequestDto);
        PatientEntity old
            = findById(UUID.fromString(sanitizedPatient.getId()));
        validatePatientBelongsToUser(old, currentUserLogin);

        PatientEntity patientEntity
            = dtoUtils.convertPatient(sanitizedPatient);
        PatientEntity toUpdate = dtoUtils.merge(old, patientEntity);
        patientEntity.getUserEntity().setId(old.getUserEntity().getId());

        PatientEntity updated = patientRepository.save(toUpdate);

        PatientResponseDto result = dtoUtils.convertPatient(updated);
        return result;
    }

    private PatientEntity findById(UUID id) {
        Optional<PatientEntity> patientOptional
            = patientRepository.findById(id);
        PatientEntity result = patientOptional.orElseThrow(() -> {
                                                               String message = messages.getPatientHaveNotAccess();
                                                               return new UserNotFoundException(message);
                                                           }
        );

        return result;
    }

    private void validatePatientBelongsToUser(PatientEntity patientEntity, String login) {
        UserEntity user = userService.getUserEntityByLogin(login);
        if (RoleNames.ADMIN.getValue().equals(user.getRole().getRoleName())) {
            return;
        }

        String patientUserId = patientEntity.getUserEntity().getId().toString();
        String userId = user.getId().toString();
        if (!userId.equals(patientUserId)) {
            String message = messages.getPatientHaveNotAccess();
            throw new UserMismatchException(message);
        }
    }

}
