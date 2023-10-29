package com.rslakra.libraryservice.service.impl;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.spring.exception.DuplicateRecordException;
import com.devamatre.framework.spring.exception.InvalidRequestException;
import com.devamatre.framework.spring.exception.NoRecordFoundException;
import com.rslakra.libraryservice.persistence.entity.Role;
import com.rslakra.libraryservice.persistence.repository.RoleRepository;
import com.rslakra.libraryservice.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Rohtash Lakra
 * @created 10/9/21 5:50 PM
 */
@Service
public class RoleServiceImpl implements RoleService {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    // roleRepository
    private final RoleRepository roleRepository;

    /**
     * @param roleRepository
     */
    @Autowired
    public RoleServiceImpl(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * @return
     */
    @Override
    public List<Role> getAll() {
        final List<Role> roles = roleRepository.findAll();
        LOGGER.debug("getAllObjects(), roles:{}", roles);
        return roles;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Role getById(Long id) {
        LOGGER.debug("getById({})", id);
        return roleRepository.findById(id)
            .orElseThrow(() -> new NoRecordFoundException("id:%d", id));

    }

    /**
     * @param role
     * @return
     */
    @Override
    public Role upsert(Role role) {
        LOGGER.debug("+upsert({})", role);
        Objects.requireNonNull(role);
        Role oldRole = role;
        if (BeanUtils.isNull(role.getId())) {
            if (BeanUtils.isNull(role.getName())) {
                throw new InvalidRequestException();
            } else if (roleRepository.findByName(role.getName()).isPresent()) {
                throw new DuplicateRecordException("name:%s", role.getName());
            }

            LOGGER.info("Creating {}", role);
        } else { // update file
            LOGGER.info("Updating {}", role);
            oldRole =
                roleRepository.findById(role.getId())
                    .orElseThrow(() -> new NoRecordFoundException("roleId:%d", role.getId()));

            // update object
            BeanUtils.copyProperties(role, oldRole, IGNORED_PROPERTIES);
        }

        // persist user
        oldRole = roleRepository.saveAndFlush(oldRole);
        LOGGER.debug("-upsert(), oldRole:{}", oldRole);
        return oldRole;
    }

    /**
     * @param objectList
     * @return
     */
    @Override
    public List<Role> upsert(List<Role> objectList) {
        final List<Role> updatedRoles = new ArrayList<>();
        objectList.forEach(role -> updatedRoles.add(upsert(role)));
//        return fileRepository.saveAllAndFlush(files);
        return updatedRoles;
    }

    /**
     * @param name
     * @return
     */
    @Override
    public Role getByName(final String name) {
        Optional<Role> role = roleRepository.findByName(name);
        if (!role.isPresent()) {
            throw new NoRecordFoundException("name:" + name);
        }

        return role.get();
    }

    /**
     * @param status
     * @return
     */
    @Override
    public List<Role> getByStatus(String status) {
        return roleRepository.getByStatus(status);
    }

    /**
     * @param pageable
     * @return
     */
    @Override
    public Page<Role> getByFilter(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    /**
     * @param id
     */
    @Override
    public void delete(final Long id) {
        LOGGER.debug("delete({})", id);
        Objects.requireNonNull(id);
        Role role = roleRepository.findById(id).orElseThrow(() -> new NoRecordFoundException("id:%d", id));
        LOGGER.info("Deleting {}", role);
        roleRepository.deleteById(id);
    }
}
