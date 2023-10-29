package com.rslakra.healthcare.routinecheckup.service.impl;

import com.rslakra.healthcare.routinecheckup.dto.ServiceDataDto;
import com.rslakra.healthcare.routinecheckup.entity.ServiceScheduleEntity;
import com.rslakra.healthcare.routinecheckup.entity.UserEntity;
import com.rslakra.healthcare.routinecheckup.repository.ServiceScheduleRepository;
import com.rslakra.healthcare.routinecheckup.service.ServiceScheduleService;
import com.rslakra.healthcare.routinecheckup.service.UserService;
import com.rslakra.healthcare.routinecheckup.utils.components.DtoUtils;
import com.rslakra.healthcare.routinecheckup.utils.exceptions.IncorrectServiceException;
import com.rslakra.healthcare.routinecheckup.utils.exceptions.ServiceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:20 PM
 */
@Service
@RequiredArgsConstructor
public class ServiceScheduleServiceImpl implements ServiceScheduleService {

    private final ServiceScheduleRepository serviceScheduleRepository;
    private final UserService userService;
    private final DtoUtils dtoUtils;

    @Override
    @Transactional
    public ServiceDataDto saveService(
        ServiceDataDto serviceDataDto,
        String login
    ) {
        if (StringUtils.isEmpty(serviceDataDto.getServiceType())) {
            throw new IncorrectServiceException("Empty type!");
        }

        UserEntity user = userService.getUserEntityByLogin(login);

        ServiceScheduleEntity entity = new ServiceScheduleEntity();
        entity.setAdditionalData(serviceDataDto.getAdditionalData());
        entity.setServiceType(serviceDataDto.getServiceType());
        entity.setUser(user);
        ServiceScheduleEntity saved = serviceScheduleRepository.save(entity);

        ServiceDataDto result = dtoUtils.convertService(saved);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceDataDto getService(UUID id, String login) {
        UserEntity user = userService.getUserEntityByLogin(login);
        Optional<ServiceScheduleEntity> serviceOpt
            = serviceScheduleRepository.findByIdAndUser(id, user);

        ServiceScheduleEntity service = serviceOpt.orElseThrow(
            () -> new ServiceNotFoundException("Service not found!")
        );
        ServiceDataDto result = dtoUtils.convertService(service);
        return result;
    }
}
