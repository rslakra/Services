package com.rslakra.liquibaseservice.component;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 8/4/21 5:45 PM
 */
public class AuditorAwareImpl implements AuditorAware<String> {

    /**
     * TODO: Use Spring Security to return currently logged in user
     * <code>return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()</code>
     *
     * @return
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Rohtash Lakra");
    }
}
