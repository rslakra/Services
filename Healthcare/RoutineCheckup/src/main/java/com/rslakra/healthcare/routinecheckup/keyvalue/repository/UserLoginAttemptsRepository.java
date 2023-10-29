package com.rslakra.healthcare.routinecheckup.keyvalue.repository;

import com.rslakra.healthcare.routinecheckup.keyvalue.entity.UserLoginAttempts;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:12 PM
 */
@Repository
public interface UserLoginAttemptsRepository extends KeyValueRepository<UserLoginAttempts, String> {

}
