package com.rslakra.healthcare.routinecheckup.service.impl;

import com.rslakra.healthcare.routinecheckup.entity.RoleEntity;
import com.rslakra.healthcare.routinecheckup.repository.RoleRepository;
import com.rslakra.healthcare.routinecheckup.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:19 PM
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<RoleEntity> findByName(String name) {
        if (name == null) {
            return Optional.empty();
        }

        Optional<RoleEntity> result
            = roleRepository.findRoleEntityByRoleName(name);
        return result;
    }
}
