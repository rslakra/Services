package com.rslakra.enablemethodsecurity.authorization;

import com.rslakra.enablemethodsecurity.services.Policy;
import com.rslakra.enablemethodsecurity.services.PolicyService;
import com.rslakra.enablemethodsecurity.services.PolicyType;
import com.rslakra.enablemethodsecurity.user.ContextUser;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class AuthorizationManagerImpl<T> implements AuthorizationManager<MethodInvocation> {

    private final AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

    /**
     * @param authentication
     * @param methodInvocation
     * @return
     */
    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, MethodInvocation methodInvocation) {
        if (hasAuthentication(authentication.get())) {
            Policy policyAnnotation = AnnotationUtils.findAnnotation(methodInvocation.getMethod(), Policy.class);
            ContextUser contextUser = (ContextUser) authentication.get().getPrincipal();
//            Predicate<PolicyType> isOpenPolicy = (policy) -> policy == PolicyType.OPEN;
//            Predicate<PolicyType> isRestrictedPolicy = (policy) -> policy == PolicyType.RESTRICTED;
            return new AuthorizationDecision(Optional.ofNullable(policyAnnotation)
                                                 .map(Policy::value)
                                                 .filter(policy -> policy == PolicyType.OPEN || (policy == PolicyType.RESTRICTED && contextUser.hasAccessToRestrictedPolicy()))
                                                 .isPresent());

        }

        return new AuthorizationDecision(false);
    }

    /**
     * @param authentication
     * @return
     */
    private boolean hasAuthentication(Authentication authentication) {
        return !isAnonymous(authentication) && authentication.isAuthenticated();
    }

    /**
     * @param authentication
     * @return
     */
    private boolean isAnonymous(Authentication authentication) {
        return (authentication == null || this.trustResolver.isAnonymous(authentication));
    }
}
