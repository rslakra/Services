package com.rslakra.libraryservice.service.impl;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.spring.exception.DuplicateRecordException;
import com.devamatre.framework.spring.exception.InvalidRequestException;
import com.devamatre.framework.spring.exception.NoRecordFoundException;
import com.rslakra.libraryservice.enums.EntityStatus;
import com.rslakra.libraryservice.enums.RoleType;
import com.rslakra.libraryservice.persistence.entity.Role;
import com.rslakra.libraryservice.persistence.entity.User;
import com.rslakra.libraryservice.persistence.repository.RoleRepository;
import com.rslakra.libraryservice.persistence.repository.UserRepository;
import com.rslakra.libraryservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * @author Rohtash Lakra
 * @created 10/9/21 5:50 PM
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    // userRepository
    private final UserRepository userRepository;
    // roleRepository
    private final RoleRepository roleRepository;

    /**
     * @param userRepository
     */
    @Autowired
    public UserServiceImpl(final UserRepository userRepository, final RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * Returns the list of all <code>T</code> objects.
     *
     * @return
     */
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * @param userId
     * @return
     */
    public User getById(final Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new NoRecordFoundException("userId:%d", userId));
    }

    /**
     * Returns the list of users by userName.
     *
     * @param userName
     * @return
     */
    public User getByUserName(String userName) {
        LOGGER.debug("getByUserName({})", userName);
        Objects.requireNonNull(userName);
        Optional<User> user = userRepository.getByUserName(userName);
        if (!user.isPresent()) {
            throw new NoRecordFoundException("userName:" + userName);
        }

        return user.get();
    }

    /**
     * Returns the list of users by firstName.
     *
     * @param firstName
     * @return
     */
    public List<User> getByFirstName(String firstName) {
        LOGGER.debug("getByFirstName({})", firstName);
        Objects.requireNonNull(firstName);
        return userRepository.getByFirstName(firstName);
    }

    /**
     * Returns the list of users by lastName.
     *
     * @param lastName
     * @return
     */
    public List<User> getByLastName(String lastName) {
        LOGGER.debug("getByLastName({})", lastName);
        Objects.requireNonNull(lastName);
        return userRepository.getByLastName(lastName);
    }

    /**
     * Returns the user by email.
     *
     * @param email
     * @return
     */
    public User getByEmail(final String email) {
        LOGGER.debug("getByEmail({})", email);
        Objects.requireNonNull(email);
        return userRepository.getByEmail(email).orElseThrow(() -> new NoRecordFoundException("email:%s", email));
    }

    /**
     * Returns the list of users by email.
     *
     * @param emailList
     * @return
     */
    @Override
    public List<User> getByEmails(List<String> emailList) {
        LOGGER.debug("getByEmails({})", emailList);
        Objects.requireNonNull(emailList);
        return userRepository.getByEmails(emailList);
    }

    /**
     * @param pageable
     * @return
     */
    public Page<User> getByFilter(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

//    /**
//     * Upsert file.
//     *
//     * @param objectType
//     * @return
//     */
//    @Override
//    public User upsert(Object objectType) {
//        return upsert((User) objectType);
//    }

    /**
     * Upsert User.
     *
     * @param user
     * @return
     */
    public User upsert(User user) {
        LOGGER.debug("+upsert({})", user);
        Objects.requireNonNull(user);
        User oldUser = user;
        if (BeanUtils.isNull(user.getId())) {
            if (BeanUtils.isNull(user.getUserName())) {
                throw new InvalidRequestException();
            } else if (userRepository.getByUserName(user.getUserName()).isPresent()) {
                throw new DuplicateRecordException("userName:%s", user.getUserName());
            }

            LOGGER.info("Creating {}", user);
        } else { // update object
            LOGGER.info("Updating {}", user);
            oldUser =
                userRepository.findById(user.getId())
                    .orElseThrow(() -> new NoRecordFoundException("userId:%d", user.getId()));

            // update object
            BeanUtils.copyProperties(user, oldUser, IGNORED_PROPERTIES);
        }

        // persist user
        oldUser = userRepository.saveAndFlush(oldUser);
        LOGGER.debug("-upsert(), oldUser:{}", oldUser);
        return oldUser;
    }

    /**
     * Upsert Users.
     *
     * @param objectList
     * @return
     */
    public List<User> upsert(List objectList) {
        LOGGER.debug("+upsert({})", objectList);
        Objects.requireNonNull(objectList);
        final List<User> updatedUsers = new ArrayList<>();
        objectList.forEach(user -> updatedUsers.add(upsert((User) user)));
        LOGGER.debug("-upsert(), updatedUsers:{}", updatedUsers);
        return updatedUsers;
    }

    /**
     * @param userId
     */
    public void delete(Long userId) {
        LOGGER.debug("delete({})", userId);
        Objects.requireNonNull(userId);
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new NoRecordFoundException("userId:%d", userId));
        LOGGER.debug("Deleting {}", user);
        userRepository.deleteById(userId);
    }

    /**
     * Returns the <code>Role</code> by <code>roleType</code>.
     *
     * @param roleType
     * @return
     */
    private Optional<Role> loadByRoleType(final RoleType roleType) {
        return roleRepository.findByName(roleType.name());
    }

    /**
     * @param user
     * @param roleTypes
     * @return
     */
    @Override
    public void addRoles(final User user, RoleType... roleTypes) {
        if (!BeanUtils.isEmpty(roleTypes)) {
            final Set<Role> userRoles = new HashSet<>();
            Arrays.asList(roleTypes).forEach(roleType -> {
                final Optional<Role> roleFromDB = loadByRoleType(roleType);
                if (roleFromDB.isPresent() && EntityStatus.ACTIVE == roleFromDB.get().getStatus()) {
                    userRoles.add(roleFromDB.get());
                }
            });

            // add roles to the user
            user.addRoles(userRoles);
        }
    }

    /**
     * @param user
     * @param roleTypes
     * @return
     */
    @Override
    public boolean hasRoles(final User user, final Set<RoleType> roleTypes) {
        return user.hasRoles(RoleType.ofRoleTypes(roleTypes));
    }
}
