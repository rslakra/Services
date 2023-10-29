package com.rslakra.services.automobile.service.impl;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.core.enums.EntityStatus;
import com.devamatre.framework.core.enums.RoleType;
import com.devamatre.framework.spring.exception.DuplicateRecordException;
import com.devamatre.framework.spring.exception.InvalidRequestException;
import com.devamatre.framework.spring.exception.NoRecordFoundException;
import com.devamatre.framework.spring.filter.Filter;
import com.devamatre.framework.spring.persistence.Operation;
import com.devamatre.framework.spring.service.AbstractServiceImpl;
import com.rslakra.services.automobile.domain.entities.AutoUser;
import com.rslakra.services.automobile.domain.repositories.UserRepository;
import com.rslakra.services.automobile.dto.LoginRequest;
import com.rslakra.services.automobile.service.AuthService;
import com.rslakra.services.automobile.service.security.context.ContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Rohtash Lakra
 * @created 4/26/23 2:07 PM
 */
@Service("authService")
public class AuthServiceImpl extends AbstractServiceImpl<AutoUser, Long> implements AuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    /**
     * @param passwordEncoder
     * @param userRepository
     */
    @Autowired
    public AuthServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        LOGGER.debug("AuthServiceImpl({}, {})", passwordEncoder, userRepository);
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    /**
     * @param autoUser
     */
    protected void addRole(AutoUser autoUser) {
        // set role
        if (BeanUtils.isEmpty(autoUser.getRole())) {
            autoUser.setStatus(RoleType.USER.name());
        } else {
            RoleType roleType = RoleType.ofString(autoUser.getRole());
            if (RoleType.ADMIN == roleType) {
                autoUser.setRole(RoleType.ADMIN.name());
            } else if (RoleType.USER == roleType) {
                autoUser.setRole(RoleType.USER.name());
            } else {
                throw new InvalidRequestException("Invalid Role!");
            }
        }
    }

    /**
     * @param autoUser
     */
    protected void addStatus(AutoUser autoUser) {
        if (BeanUtils.isEmpty(autoUser.getStatus())) {
            autoUser.setStatus(EntityStatus.INACTIVE.name());
        } else {
            EntityStatus entityStatus = EntityStatus.ofString(autoUser.getStatus());
            if (EntityStatus.ACTIVE == entityStatus) {
                autoUser.setStatus(EntityStatus.ACTIVE.name());
            } else if (EntityStatus.INACTIVE == entityStatus) {
                autoUser.setStatus(EntityStatus.INACTIVE.name());
            } else if (EntityStatus.DELETED == entityStatus) {
                autoUser.setStatus(EntityStatus.DELETED.name());
            } else {
                throw new InvalidRequestException("Invalid Status!");
            }
        }
    }

    /**
     * @param autoUser
     */
    protected void encodePassword(AutoUser autoUser) {
        if (BeanUtils.isEmpty(autoUser.getPassword())) {
            throw new InvalidRequestException("The password should provide!");
        }

        autoUser.setRawPassword(autoUser.getPassword());
        autoUser.setPassword(passwordEncoder.encode(autoUser.getPassword()));
    }

    /**
     * @param operation
     * @param autoUser
     * @return
     */
    @Override
    public AutoUser validate(Operation operation, AutoUser autoUser) {
        return null;
    }

    /**
     * Registers the new autoUser.
     *
     * @param autoUser
     * @return
     */
    @Override
    public AutoUser create(AutoUser autoUser) {
        LOGGER.debug("+create({})", autoUser);
        if (BeanUtils.isNull(autoUser)) {
            throw new InvalidRequestException("Invalid Request!");
        }

        Optional<AutoUser> autoUserOptional = userRepository.findByEmail(autoUser.getEmail());
        if (autoUserOptional.isPresent()) {
            throw new DuplicateRecordException("userName:%s", autoUser.getEmail());
        } else {
            // set role
            addRole(autoUser);
            // set status
            addStatus(autoUser);
            // encode password
            encodePassword(autoUser);
        }

        // save user
        autoUser = userRepository.save(autoUser);
        Authentication authentication = ContextUtils.authenticate(autoUser);
        LOGGER.debug("-create(), authentication: {}, autoUser: {}", authentication, autoUser);
        return autoUser;
    }

    /**
     * @param autoUsers
     * @return
     */
    @Override
    public List<AutoUser> create(List<AutoUser> autoUsers) {
        LOGGER.debug("+create({})", autoUsers);
        final List<AutoUser> autoUserList = new ArrayList<>();
        if (BeanUtils.isNotEmpty(autoUsers)) {
            autoUsers.forEach(autoUser -> autoUserList.add(create(autoUser)));
        }

        LOGGER.debug("+create(), autoUserList: {}", autoUserList);
        return autoUserList;
    }

    /**
     * @param autoUser
     * @return
     */
    @Override
    public AutoUser register(AutoUser autoUser) {
        LOGGER.debug("+register({})", autoUser);
        autoUser = create(autoUser);
        LOGGER.debug("-register(), autoUser: {}", autoUser);
        return autoUser;
    }

    /**
     * Login user.
     *
     * @param loginRequest
     * @return
     */
    @Override
    public Optional<AutoUser> login(LoginRequest loginRequest) {
        LOGGER.debug("+login({})", loginRequest);
        Optional<AutoUser> autoUserOptional = userRepository.findByEmail(loginRequest.getUsername());
        if (autoUserOptional.isPresent()) {
            LOGGER.debug("autoUserOptional: {}", autoUserOptional);
            Authentication authentication = ContextUtils.authenticate(autoUserOptional.get());
            LOGGER.debug("authentication: {}", authentication);
        }

        LOGGER.debug("-login() autoUserOptional: {}", autoUserOptional);
        return autoUserOptional;
    }

    /**
     * @return
     */
    @Override
    public List<AutoUser> getAll() {
        return userRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public AutoUser getById(final Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NoRecordFoundException("userId:%d", id));
    }

    /**
     * @param filter
     * @return
     */
    @Override
    public List<AutoUser> getByFilter(Filter filter) {
        return userRepository.findAll();
    }

    /**
     * @param filter
     * @param pageable
     * @return
     */
    @Override
    public Page<AutoUser> getByFilter(Filter filter, Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    /**
     * @param autoUser
     * @return
     */
    @Override
    public AutoUser update(AutoUser autoUser) {
        return null;
    }

    /**
     * @param autoUsers
     * @return
     */
    @Override
    public List<AutoUser> update(List<AutoUser> autoUsers) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public AutoUser delete(Long id) {
        AutoUser autoUser = getById(id);
        userRepository.delete(autoUser);
        return autoUser;
    }

}
