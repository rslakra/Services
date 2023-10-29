package com.rslakra.healthcare.routinecheckup.repository;


import com.rslakra.healthcare.routinecheckup.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:04 PM
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {

    Optional<RoleEntity> findRoleEntityByRoleName(String roleName);

}
