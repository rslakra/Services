package com.rslakra.healthcare.routinecheckup.service;


import com.rslakra.healthcare.routinecheckup.dto.ServiceDataDto;

import java.util.UUID;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:16 PM
 */
public interface ServiceScheduleService {

    ServiceDataDto saveService(ServiceDataDto serviceDataDto, String login);

    ServiceDataDto getService(UUID id, String login);

}
