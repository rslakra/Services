package com.rslakra.healthcare.routinecheckup.repository;


import com.rslakra.healthcare.routinecheckup.entity.UserEntity;
import com.rslakra.healthcare.routinecheckup.entity.UserFileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:04 PM
 */
@Repository
public interface UserFileRepository
    extends CrudRepository<UserFileEntity, UUID> {

    Optional<UserFileEntity> findByOwnerAndOriginalFileName(
        UserEntity user,
        String fileName
    );

    List<UserFileEntity> getAllByOwner(UserEntity owner);

}
