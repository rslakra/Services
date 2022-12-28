package com.rslakra.enablemethodsecurity.authentication;

import com.rslakra.enablemethodsecurity.user.ContextUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final Map<String, ContextUser> userMap = new HashMap<>();


    /**
     * @param passwordEncoder
     */
    public UserDetailsServiceImpl(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        addUser("user", "userPass", false, "USER");
        addUser("admin", "adminPass", true, "ADMIN", "USER");
//        userMap.put("user", createUser("user", passwordEncoder.encode("userPass"), false, "USER"));
//        userMap.put("admin", createUser("admin", passwordEncoder.encode("adminPass"), true, "ADMIN", "USER"));
    }

    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return Optional.ofNullable(userMap.get(username))
            .orElseThrow(() -> new UsernameNotFoundException("User " + username + " does not exists"));
    }

    /**
     * @param userName
     * @param password
     * @param withRestrictedPolicy
     * @param roles
     */
    private void addUser(String userName, String password, boolean withRestrictedPolicy, String... roles) {
        userMap.put(userName, createUser(userName, passwordEncoder.encode(password), withRestrictedPolicy, roles));
    }

    /**
     * @param userName
     * @param password
     * @param withRestrictedPolicy
     * @param roles
     * @return
     */
    private ContextUser createUser(String userName, String password, boolean withRestrictedPolicy, String... roles) {
        return ContextUser.builder()
            .withUserName(userName)
            .withPassword(password)
            .withRoles(roles)
//            .withGrantedAuthorities(ContextUser.fromRoles(roles))
            .withAccessToRestrictedPolicy(withRestrictedPolicy);
    }
}
