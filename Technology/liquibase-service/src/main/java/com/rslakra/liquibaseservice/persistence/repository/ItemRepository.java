package com.rslakra.liquibaseservice.persistence.repository;

import com.rslakra.liquibaseservice.persistence.entity.Item;
import org.springframework.stereotype.Repository;

/**
 * @author Rohtash Lakra
 */
@Repository
public interface ItemRepository extends BaseRepository<Item, Long> {

}
