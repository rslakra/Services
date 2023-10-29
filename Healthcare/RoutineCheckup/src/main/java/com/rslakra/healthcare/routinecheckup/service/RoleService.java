package com.rslakra.healthcare.routinecheckup.service;


import com.rslakra.healthcare.routinecheckup.entity.RoleEntity;

import java.util.Optional;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:15 PM
 */
public interface RoleService {

    Optional<RoleEntity> findByName(String name);

}
