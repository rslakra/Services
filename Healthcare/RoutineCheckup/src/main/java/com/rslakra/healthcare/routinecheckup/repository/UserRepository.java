package com.rslakra.healthcare.routinecheckup.repository;

import com.rslakra.healthcare.routinecheckup.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:04 PM
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, UUID> {

    Optional<UserEntity> findByLogin(String login);

    void deleteUserEntitiesByCreationTimeBeforeAndIsTemporary(
        Date registrationExpiredDate,
        Boolean isTemporary
    );

    boolean existsByLogin(String login);

    boolean existsByMail(String mail);

}
