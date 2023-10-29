package com.rslakra.services.automobile.domain.repositories;

import com.rslakra.services.automobile.domain.entities.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Rohtash Lakra (rohtash.singh@gmail.com)
 * @since 09-16-2019 1:39:54 PM
 */
@Repository
public interface ServiceTypeRepository extends JpaRepository<ServiceType, Long> {

    /**
     * @param name
     * @return
     */
    public ServiceType findByName(String name);
}
