package com.rslakra.healthcare.routinecheckup.repository;


import com.rslakra.healthcare.routinecheckup.entity.PatientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:04 PM
 */
@Repository
public interface PatientRepository extends CrudRepository<PatientEntity, UUID> {

}
