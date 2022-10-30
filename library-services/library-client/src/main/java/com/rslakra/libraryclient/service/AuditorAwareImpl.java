//package com.rslakra.libraryclient.service;
//
//import org.springframework.data.domain.AuditorAware;
////import org.springframework.security.core.Authentication;
////import org.springframework.security.core.context.SecurityContextHolder;
//
//import java.util.Optional;
//
///**
// * @author Rohtash Lakra
// * @created 8/4/21 5:45 PM
// */
//public class AuditorAwareImpl implements AuditorAware<String> {
//
//    // UNKNOWN
//    private final Optional<String> UNKNOWN = Optional.of("UNKNOWN");
//
////    /**
////     * Returns the <code>Authentication</code> object.
////     *
////     * @return
////     */
////    public static Authentication getAuthentication() {
////        return SecurityContextHolder.getContext().getAuthentication();
////    }
////
////    /**
////     * @return
////     */
////    public static boolean isAuthenticated() {
////        return getAuthentication().isAuthenticated();
////    }
//
//    /**
//     * TODO: Use Spring Security to return currently logged in user
//     * <code>return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()</code>
//     *
//     * @return
//     */
//    @Override
//    public Optional<String> getCurrentAuditor() {
////        Authentication authentication = getAuthentication();
////        return (authentication != null ? Optional.of(authentication.getPrincipal().toString()) : UNKNOWN);
//        return UNKNOWN;
//    }
//}
