package com.rslakra.services.automobile.service.security.impl;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.core.enums.RoleType;
import com.devamatre.framework.spring.exception.NoRecordFoundException;
import com.rslakra.services.automobile.domain.entities.AutoUser;
import com.rslakra.services.automobile.domain.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

/**
 * @author Rohtash Lakra (rohtash.singh@gmail.com)
 * @since 01-04-2019 1:38:56 PM
 */
@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    /**
     * @param passwordEncoder
     * @param userRepository
     */
    @Autowired
    public UserDetailsServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        super();
        LOGGER.debug("UserDetailsServiceImpl({}, {})", passwordEncoder, userRepository);
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    /**
     * Returns the list of <code>List<GrantedAuthority></code>.
     *
     * @param privileges
     * @return
     */
    private List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
        LOGGER.debug("+getGrantedAuthorities()", privileges);
        final List<GrantedAuthority> authorities = new ArrayList<>();
        if (BeanUtils.isNotEmpty(privileges)) {
            privileges.forEach(privilege -> authorities.add(new SimpleGrantedAuthority(privilege)));
        }
        LOGGER.debug("-getGrantedAuthorities(), authorities: {}", authorities);
        return authorities;
    }

    /**
     * @param roleTypes
     * @return
     */
    private List<String> getPrivileges(final Collection<RoleType> roleTypes) {
        final List<String> privileges = new ArrayList<>();
//        final List<Privilege> collection = new ArrayList<>();
//        for (final Role role : roleTypes) {
//            privileges.add(role.getName());
//            collection.addAll(role.getPrivileges());
//        }
//        for (final Privilege item : collection) {
//            privileges.add(item.getName());
//        }

        if (BeanUtils.isNotEmpty(roleTypes)) {
            roleTypes.forEach(roleType -> privileges.add(roleType.name()));
        }

        return privileges;
    }

    /**
     * @param roleTypes
     * @return
     */
    private Collection<? extends GrantedAuthority> getAuthorities(final Collection<RoleType> roleTypes) {
        return getGrantedAuthorities(getPrivileges(roleTypes));
    }

    /**
     * Returns the <code>UserDetails</code> object based on the <code>username</code>.
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        LOGGER.debug("+loadUserByUsername({})", username);
//        if (loginAttemptService.isBlocked()) {
//            throw new RuntimeException("blocked");
//        }

        AutoUser autoUser = null;
        Optional<AutoUser> autoUserOptional = userRepository.findByEmail(username);
        if (!autoUserOptional.isPresent()) {
            throw new NoRecordFoundException("username:%s", username);
        }

        // build UserDetails object
        autoUser = autoUserOptional.get();
        return autoUser;
    }

}
