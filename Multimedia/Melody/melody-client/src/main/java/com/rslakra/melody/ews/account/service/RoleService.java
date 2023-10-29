package com.rslakra.melody.ews.account.service;

import com.rslakra.melody.ews.account.payload.dto.Role;
import com.rslakra.melody.ews.framework.client.AbstractClientService;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 8/20/21 8:11 PM
 */
public interface RoleService extends AbstractClientService<Role> {

    String ROLES = "roles";
    String ROLES_BATCH = "roles/batch";
    String ROLE_BY_ID = "roles/{id}";
    String ROLES_FILTER = "roles/filter";

    /**
     * Returns the role by name.
     *
     * @param name
     * @return
     */
    public Role getByName(String name);

    /**
     * Returns the list of roles by status.
     *
     * @param status
     * @return
     */
    public List<Role> getByStatus(String status);

}
