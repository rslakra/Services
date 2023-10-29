package com.rslakra.healthcare.routinecheckup.service.impl;

import com.rslakra.healthcare.routinecheckup.keyvalue.entity.UserLoginAttempts;
import com.rslakra.healthcare.routinecheckup.keyvalue.repository.UserLoginAttemptsRepository;
import com.rslakra.healthcare.routinecheckup.service.UserLoginAttemptsService;
import com.rslakra.healthcare.routinecheckup.utils.components.holder.LoginAttemptsConstants;
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
public class UserLoginAttemptsServiceImpl implements UserLoginAttemptsService {

    private final UserLoginAttemptsRepository userLoginAttemptsRepository;

    private final LoginAttemptsConstants loginAttemptsConstants;

    @Override
    public UserLoginAttempts commitNewAttempt(String userIp) {
        Optional<UserLoginAttempts> attemptsOptional
            = userLoginAttemptsRepository.findById(userIp);

        if (attemptsOptional.isPresent()) {
            UserLoginAttempts attempts = attemptsOptional.get();
            attempts.setCurrentAttemptsCount(
                getCurrentAttemptCount(attempts)
            );
            attempts.setLastAttemptDate(new Date());
            userLoginAttemptsRepository.save(attempts);

            return attempts;
        }

        UserLoginAttempts attempts = new UserLoginAttempts(userIp);
        UserLoginAttempts result
            = userLoginAttemptsRepository.save(attempts);

        return result;
    }

    @Override
    public boolean isExtraCurrentLogin(String userIp) {
        UserLoginAttempts attempts = commitNewAttempt(userIp);

        boolean result
            = attempts.getCurrentAttemptsCount()
              > loginAttemptsConstants.getMaxAttemptsCount();
        return result;
    }

    private Integer getCurrentAttemptCount(UserLoginAttempts attempts) {
        Date lastAttemptDate = attempts.getLastAttemptDate();
        Date currentDate = new Date();
        Date expirationDate = new Date(
            lastAttemptDate.getTime()
            + loginAttemptsConstants.getMaxAllowableTimeSpanMS()
        );

        if (currentDate.after(expirationDate)) {
            return 1;
        } else {
            return attempts.getCurrentAttemptsCount() + 1;
        }
    }

}
