package com.rslakra.healthcare.routinecheckup.repository;


import com.rslakra.healthcare.routinecheckup.entity.ServiceScheduleEntity;
import com.rslakra.healthcare.routinecheckup.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:04 PM
 */
@Repository
public interface ServiceScheduleRepository
    extends CrudRepository<ServiceScheduleEntity, UUID> {

    Optional<ServiceScheduleEntity> findByIdAndUser(UUID id, UserEntity user);
}
