package com.rslakra.iws.businessservice.marketing.persistence.repository;

import com.devamatre.framework.spring.persistence.repository.BaseRepository;
import com.rslakra.iws.businessservice.marketing.persistence.entity.Marketing;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Rohtash Lakra
 * @created 2/7/23 2:25 PM
 */
@Repository
public interface MarketingRepository extends BaseRepository<Marketing, Long> {

    /**
     * @param userId
     * @return
     */
    Optional<Marketing> findByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);

    /**
     * @param userId
     * @return
     */
    List<Marketing> findAllByUserId(@Param("userId") Long userId);
}
