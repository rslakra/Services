//package com.rslakra.swaggerservice.service;
//
//import com.rslakra.swaggerservice.entity.User;
//import com.rslakra.swaggerservice.exception.NoRecordFoundException;
//import com.rslakra.swaggerservice.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
///**
// * @author Rohtash Lakra
// * @created 8/20/21 6:00 PM
// */
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public UserDetailsServiceImpl() {
//    }
//
//    /**
//     * @param userName
//     * @return
//     * @throws NoRecordFoundException
//     */
//    @Override
//    public UserDetails loadUserByUsername(String userName)
//        throws NoRecordFoundException {
//        User user = userRepository.getByEmail(userName);
//        if (user == null) {
//            throw new NoRecordFoundException("Could not find user!");
//        }
//
//        return new UserDetailsImpl(user);
//    }
//
//}
