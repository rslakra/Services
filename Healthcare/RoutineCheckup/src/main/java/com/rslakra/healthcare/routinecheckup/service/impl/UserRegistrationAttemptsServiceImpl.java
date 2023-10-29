package com.rslakra.healthcare.routinecheckup.service.impl;

import com.rslakra.healthcare.routinecheckup.keyvalue.entity.UserRegistrationAttempts;
import com.rslakra.healthcare.routinecheckup.keyvalue.repository.UserRegistrationAttemptsRepository;
import com.rslakra.healthcare.routinecheckup.service.UserRegistrationAttemptsService;
import com.rslakra.healthcare.routinecheckup.utils.components.holder.RegistrationConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:20 PM
 */
@Service
@RequiredArgsConstructor
public class UserRegistrationAttemptsServiceImpl
    implements UserRegistrationAttemptsService {

    private final UserRegistrationAttemptsRepository
        userRegistrationAttemptsRepository;

    private final RegistrationConstants registrationConstants;

    @Override
    public UserRegistrationAttempts commitNewAttempt(String userIp) {
        Optional<UserRegistrationAttempts> attemptsOptional
            = userRegistrationAttemptsRepository.findById(userIp);

        if (attemptsOptional.isPresent()) {
            UserRegistrationAttempts attempts = attemptsOptional.get();
            attempts.setCurrentAttemptsCount(
                getCurrentAttemptCount(attempts)
            );
            attempts.setLastAttemptDate(new Date());
            userRegistrationAttemptsRepository.save(attempts);

            return attempts;
        }

        UserRegistrationAttempts attempts
            = new UserRegistrationAttempts(userIp);
        UserRegistrationAttempts result
            = userRegistrationAttemptsRepository.save(attempts);

        return result;
    }

    @Override
    public boolean isExtraCurrentRegistration(String userIp) {
        UserRegistrationAttempts attempts = commitNewAttempt(userIp);

        return attempts.getCurrentAttemptsCount()
               > registrationConstants.getMaxAttemptsCount();
    }

    @Override
    public boolean isExtraLastRegistration(String userIp) {
        Optional<UserRegistrationAttempts> attemptsOptional
            = userRegistrationAttemptsRepository.findById(userIp);
        UserRegistrationAttempts attempts = attemptsOptional.orElseGet(() -> {
            UserRegistrationAttempts newAttempt
                = new UserRegistrationAttempts(userIp);
            UserRegistrationAttempts saved
                = userRegistrationAttemptsRepository.save(newAttempt);

            return saved;
        });

        return attempts.getCurrentAttemptsCount()
               > registrationConstants.getMaxAttemptsCount();
    }

    private Integer getCurrentAttemptCount(UserRegistrationAttempts attempts) {
        Date lastAttemptDate = attempts.getLastAttemptDate();
        Date currentDate = new Date();
        Date expirationDate = new Date(
            lastAttemptDate.getTime()
            + registrationConstants
                .getMaxAllowableTimeSpanMS()
        );

        if (currentDate.after(expirationDate)) {
            return 1;
        } else {
            return attempts.getCurrentAttemptsCount() + 1;
        }
    }

}
