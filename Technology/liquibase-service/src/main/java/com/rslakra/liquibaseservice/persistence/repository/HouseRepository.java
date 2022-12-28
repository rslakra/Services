package com.rslakra.liquibaseservice.persistence.repository;

import com.rslakra.liquibaseservice.persistence.entity.House;
import org.springframework.stereotype.Repository;

/**
 * @author Rohtash Lakra
 */
@Repository
public interface HouseRepository extends BaseRepository<House, Long> {

}
