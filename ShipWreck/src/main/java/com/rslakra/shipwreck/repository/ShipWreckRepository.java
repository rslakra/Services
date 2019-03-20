/**
 * 
 */
package com.rslakra.shipwreck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rslakra.shipwreck.model.ShipWreck;

/**
 * @author Rohtash Singh Lakra
 * @version 1.0.0
 *
 */
@Repository
public interface ShipWreckRepository extends JpaRepository<ShipWreck, Long> {

}
