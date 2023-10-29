package com.rslakra.melody.ews.account.service.impl;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.core.Payload;
import com.devamatre.framework.spring.client.ApiRestClient;
import com.devamatre.framework.spring.exception.InvalidRequestException;
import com.devamatre.framework.spring.persistence.Operation;
import com.rslakra.melody.ews.account.payload.dto.Role;
import com.rslakra.melody.ews.account.service.RoleService;
import com.rslakra.melody.ews.framework.client.impl.AbstractClientServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Rohtash Lakra
 * @created 10/9/21 5:50 PM
 */
@Service
public class RoleServiceImpl extends AbstractClientServiceImpl<Role> implements RoleService {

    // apiRestClient
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    // apiRestClient
    private final ApiRestClient apiRestClient;

    /**
     * @param apiRestClient
     */
    @Autowired
    public RoleServiceImpl(final ApiRestClient apiRestClient) {
        LOGGER.debug("RoleServiceImpl({})", apiRestClient);
        this.apiRestClient = apiRestClient;
    }

    /**
     * Validates the <code>T</code> object.
     *
     * @param operation
     * @param role
     * @return
     */
    @Override
    public Role validate(Operation operation, Role role) {
        LOGGER.debug("+validate({}, {})", operation, role);

        switch (operation) {
            case CREATE:
                if (BeanUtils.isEmpty(role.getName())) {
                    throw new InvalidRequestException("The role's name should provide!");
                }

                break;

            case UPDATE:
                if (BeanUtils.isNull(role.getId())) {
                    throw new InvalidRequestException("The role's ID should provide!");
                }

                break;

            default:
                throw new InvalidRequestException("Unsupported Operation!");
        }

        LOGGER.debug("-validate(), role:{}", role);
        return role;
    }

    /**
     * @param role
     * @return
     */
    @Override
    public Role create(Role role) {
        LOGGER.debug("+create({})", role);
        if (BeanUtils.isNull(role)) {
            throw new InvalidRequestException("The role should provide!");
        }

        validate(Operation.CREATE, role);
        role = apiRestClient.doPost(ROLES, role, Role.class);
        LOGGER.debug("-create(), role:{}", role);
        return role;
    }

    /**
     * @param roles
     * @return
     */
    @Override
    public List<Role> create(List<Role> roles) {
        LOGGER.debug("+create({})", roles);
        if (BeanUtils.isEmpty(roles)) {
            throw new InvalidRequestException("The users should provide!");
        }

        roles.forEach(role -> validate(Operation.CREATE, role));
        roles = apiRestClient.doPost(ROLES_BATCH, roles, List.class);
        LOGGER.debug("-create(), roles:{}", roles);
        return roles;
    }

    /**
     * @return
     */
    @Override
    public List<Role> getAll() {
        LOGGER.debug("+getAll()");
        // note: get results by array and convert to list.
        List<Role> roles;
        // helps to display empty ui page.
        try {
            roles = Arrays.asList(apiRestClient.doGet(ROLES, Role[].class));
        } catch (Exception ex) {
            LOGGER.error(ex.getLocalizedMessage(), ex);
            // helps to display empty ui page.
            roles = new ArrayList<>();
        }

        LOGGER.debug("-getAll(), roles:{}", roles);
        return roles;
    }

    /**
     * @param filters
     * @return
     */
    @Override
    public List<Role> getByFilter(Map<String, Object> filters) {
        LOGGER.debug("+getByFilter({})", filters);
        // rest/roles/filter?id=3
        final Role[] roles = apiRestClient.doGet(ROLES_FILTER, Role[].class, filters);
        LOGGER.debug("-getByFilter(), roles:{}", roles);
        return Arrays.asList(roles);
    }

    /**
     * @param filters
     * @param pageable
     * @return
     */
    @Override
    public List<Role> getByFilter(Map<String, Object> filters, Pageable pageable) {
        LOGGER.debug("+getByFilter({}, {})", filters, pageable);
        List<Role> roles = getByFilter(filters);
        LOGGER.debug("-getByFilter(), roles:{}", roles);
        return roles;
    }

    /**
     * Returns the <code>T</code> object by <code>id</code>.
     *
     * @param id
     * @return
     */
    @Override
    public Role getById(Long id) {
        LOGGER.debug("+getById({})", id);
        // rest/roles/filter?id=16
        Role role = (Role) getByFilter(Payload.newBuilder().ofPair("id", id)).stream().findFirst().orElse(null);
        LOGGER.debug("-getById(), role:{}", role);
        return role;
    }

    /**
     * @param role
     * @return
     */
    @Override
    public Role update(Role role) {
        LOGGER.debug("+update({})", role);
        if (BeanUtils.isEmpty(role)) {
            throw new InvalidRequestException("The role should provide!");
        }

        validate(Operation.UPDATE, role);
        apiRestClient.doPut(ROLES, role, Role.class);

        LOGGER.debug("-update(), role:{}", role);
        return role;
    }

    /**
     * @param roles
     * @return
     */
    @Override
    public List<Role> update(List<Role> roles) {
        LOGGER.debug("+update({})", roles);
        if (BeanUtils.isEmpty(roles)) {
            throw new InvalidRequestException("The roles should provide!");
        }

        roles.forEach(role -> validate(Operation.UPDATE, role));
        apiRestClient.doPut(ROLES_BATCH, roles, List.class);

        LOGGER.debug("-update(), roles:{}", roles);
        return roles;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Role delete(Long id) {
        LOGGER.debug("+delete({})", id);
        BeanUtils.assertNonNull(id, "The role's id should provide!");
        Role role = null;
        apiRestClient.doDelete(ROLE_BY_ID, Payload.newBuilder().ofPair("id", id));
        LOGGER.debug("-delete(), role:{}", role);
        return role;
    }

    /**
     * Returns the role by name.
     *
     * @param name
     * @return
     */
    @Override
    public Role getByName(String name) {
        LOGGER.debug("+getByName({})", name);
        BeanUtils.assertNonNull(name, "The role name should provide!");
        Role role = (Role) getByFilter(Payload.newBuilder().ofPair("name", name)).stream().findFirst().orElse(null);
        LOGGER.debug("-getByName(), role:{}", role);
        return role;
    }

    /**
     * Returns the list of roles by status.
     *
     * @param status
     * @return
     */
    @Override
    public List<Role> getByStatus(String status) {
        LOGGER.debug("+getByStatus({})", status);
        BeanUtils.assertNonNull(status, "The role status should provide!");
        List<Role> roles = getByFilter(Payload.newBuilder().ofPair("status", status));
        LOGGER.debug("-getByStatus(), roles: {}", roles);
        return roles;
    }
}
