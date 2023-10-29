package com.rslakra.healthcare.routinecheckup.repository;


import com.rslakra.healthcare.routinecheckup.entity.DoctorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:03 PM
 */
@Repository
public interface DoctorRepository extends CrudRepository<DoctorEntity, UUID> {

    @Query(
        "from " +
        "DoctorEntity d " +
        "where " +
        "d.speciality like %:str% or " +
        "concat(d.userEntity.lastName, ' ', d.userEntity.firstName) " +
        "like %:str% or " +
        "concat(d.userEntity.lastName, ' ', d.userEntity.firstName) " +
        "like %:str%"
    )
    List<DoctorEntity> findAllBySearchString(@Param("str") String search);

}
