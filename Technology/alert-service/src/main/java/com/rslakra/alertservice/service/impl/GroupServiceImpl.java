package com.rslakra.alertservice.service.impl;

import com.rslakra.alertservice.persistence.domain.Group;
import com.rslakra.alertservice.service.GroupService;

import java.util.Set;
import java.util.UUID;

/**
 * @author Rohtash Lakra
 * @created 9/15/23 2:36 PM
 */
public class GroupServiceImpl implements GroupService {

    /**
     * Creates a new group with the provided details and returns the Group Id.
     *
     * @param group
     */
    @Override
    public void addGroup(Group group) {

    }

    /**
     * Allows a user to join a specific group by providing the Group Id.
     *
     * @param groupId
     * @param userIds
     */
    @Override
    public void joinGroup(UUID groupId, UUID... userIds) {

    }

    /**
     * Allows a user to leave a specific group by providing the Group Id.
     *
     * @param groupId
     * @param userIds
     */
    @Override
    public void leaveGroup(UUID groupId, UUID... userIds) {

    }

    /**
     * @param groups
     * @return
     */
    @Override
    public Group leaveGroup(Group... groups) {
        return null;
    }

    /**
     * Retrieves the list of groups in which a user is a member by providing the User Id.
     *
     * @return
     */
    @Override
    public Set<Group> getGroups() {
        return null;
    }

    /**
     * Retrieves the list of members belonging to a specific group by providing the Group Id.
     *
     * @param groupId
     * @return
     */
    @Override
    public Set<UUID> getMembers(UUID groupId) {
        return null;
    }

    /**
     * Retrieves the messages sent in a specific group by providing the Group Id.
     *
     * @param groupId
     * @param timeStamp
     * @return
     */
    @Override
    public Set<UUID> getGroupMessages(UUID groupId, Long timeStamp) {
        return null;
    }
}
