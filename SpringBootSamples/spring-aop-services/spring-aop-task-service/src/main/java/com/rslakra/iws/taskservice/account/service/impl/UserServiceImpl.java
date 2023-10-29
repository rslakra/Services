package com.rslakra.iws.taskservice.account.service.impl;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.core.Payload;
import com.devamatre.framework.core.enums.EntityStatus;
import com.devamatre.framework.core.enums.RoleType;
import com.devamatre.framework.spring.exception.DuplicateRecordException;
import com.devamatre.framework.spring.exception.InvalidRequestException;
import com.devamatre.framework.spring.exception.NoRecordFoundException;
import com.devamatre.framework.spring.filter.Filter;
import com.devamatre.framework.spring.persistence.Operation;
import com.devamatre.framework.spring.service.AbstractServiceImpl;
import com.rslakra.iws.taskservice.account.persistence.entity.Role;
import com.rslakra.iws.taskservice.account.persistence.entity.User;
import com.rslakra.iws.taskservice.account.persistence.repository.RoleRepository;
import com.rslakra.iws.taskservice.account.persistence.repository.UserRepository;
import com.rslakra.iws.taskservice.account.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
public class UserServiceImpl extends AbstractServiceImpl<User, Long> implements UserService {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    // roleRepository
    private final RoleRepository roleRepository;
    // userRepository
    private final UserRepository userRepository;

    /**
     * @param userRepository
     */
    @Autowired
    public UserServiceImpl(final RoleRepository roleRepository, final UserRepository userRepository) {
        LOGGER.debug("UserServiceImpl({}, {})", roleRepository, userRepository);
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    /**
     * Validates the <code>T</code> object.
     *
     * @param operation
     * @param user
     * @return
     */
    @Override
    public User validate(Operation operation, User user) {
        LOGGER.debug("+validate({}, {})", operation, user);
        switch (operation) {
            case CREATE: {
                if (BeanUtils.isEmpty(user.getEmail())) {
                    throw new InvalidRequestException("The user's email should provide!");
                } else if (BeanUtils.isEmpty(user.getFirstName())) {
                    throw new InvalidRequestException("The user's firstName should provide!");
                } else if (BeanUtils.isEmpty(user.getLastName())) {
                    throw new InvalidRequestException("The user's lastName should provide!");
                }

                // check task exists for this song
                if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                    throw new DuplicateRecordException("user");
                }
            }

            break;

            case UPDATE: {
                if (BeanUtils.isNull(user.getId())) {
                    throw new InvalidRequestException("The user's ID should provide!");
                }

                if (BeanUtils.isNotEmpty(user.getEmail())) {
                    // check task exists for this song
                    Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
                    if (userOptional.isPresent() && !userOptional.get().getId().equals(user.getId())) {
                        throw new DuplicateRecordException("user");
                    }
                }

                // update object
                User oldUser = getById(user.getId());
                BeanUtils.copyProperties(user, oldUser, IGNORED_PROPERTIES);
                user = oldUser;
            }

            break;

            default:
                throw new InvalidRequestException("Unsupported Operation!");
        }

        LOGGER.debug("-validate(), user: {}", user);
        return user;
    }

    /**
     * Creates the <code>T</code> object.
     *
     * @param user
     * @return
     */
    @Override
    public User create(User user) {
        LOGGER.debug("+create({})", user);
        user = validate(Operation.CREATE, user);
        user = userRepository.save(user);
        LOGGER.debug("-create(), user: {}", user);
        return user;
    }

    /**
     * Creates the <code>List<T></code> objects.
     *
     * @param users
     * @return
     */
    @Override
    public List<User> create(List<User> users) {
        LOGGER.debug("+create({})", users);
        if (BeanUtils.isEmpty(users)) {
            throw new InvalidRequestException("The users should provide!");
        }

        users.forEach(user -> validate(Operation.CREATE, user));
        users = userRepository.saveAll(users);

        LOGGER.debug("-create(), users: {}", users);
        return users;
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
     * Returns the pageable <code>T</code> object by <code>pageable</code> filter.
     *
     * @param filter
     * @return
     */
    @Override
    public List<User> getByFilter(Filter filter) {
        return userRepository.findAll();
    }

    /**
     * Returns the pageable <code>T</code> object by <code>pageable</code> filter.
     *
     * @param filter
     * @param pageable
     * @return
     */
    @Override
    public Page<User> getByFilter(Filter filter, Pageable pageable) {
        return userRepository.findAll(pageable);
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
        return userRepository.findByEmail(email).orElseThrow(() -> new NoRecordFoundException("email:%s", email));
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
    public Page<User> getByFilter(Payload payload, Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    /**
     * Updates the <code>T</code> object.
     *
     * @param user
     * @return
     */
    @Override
    public User update(User user) {
        LOGGER.debug("+update({})", user);
        user = validate(Operation.UPDATE, user);
        user = userRepository.save(user);
        LOGGER.debug("-update(), user: {}", user);
        return user;
    }

    /**
     * Updates the <code>List<T></code> objects.
     *
     * @param users
     * @return
     */
    @Override
    public List<User> update(List<User> users) {
        LOGGER.debug("+update({})", users);
        if (BeanUtils.isEmpty(users)) {
            throw new InvalidRequestException("The users should provide!");
        }

        users.forEach(user -> validate(Operation.UPDATE, user));
        users = userRepository.saveAll(users);

        LOGGER.debug("-update(), users: {}", users);
        return users;
    }

    /**
     * @param userId
     */
    public User delete(Long userId) {
        LOGGER.debug("+delete({})", userId);
        Objects.requireNonNull(userId);
        User user = getById(userId);
        LOGGER.info("Deleting {}", user);
        userRepository.deleteById(userId);
        LOGGER.debug("-delete(), user: {}", user);
        return user;
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
        LOGGER.debug("+addRoles({}, {})", user, roleTypes);
        if (BeanUtils.isNotEmpty(roleTypes)) {
            final Set<Role> userRoles = new HashSet<>();
            Arrays.asList(roleTypes).forEach(roleType -> {
                final Optional<Role> roleOptional = loadByRoleType(roleType);
                if (roleOptional.isPresent() && EntityStatus.ACTIVE == roleOptional.get().getStatus()) {
                    userRoles.add(roleOptional.get());
                }
            });

            // add roles to the user
            user.addRoles(userRoles);
        }
        LOGGER.debug("-addRoles()");
    }

    /**
     * @param user
     * @param roleTypes
     * @return
     */
    @Override
    public boolean hasRoles(final User user, final Set<RoleType> roleTypes) {
        return user.hasRoles(Role.ofRoleTypes(roleTypes));
    }
}
