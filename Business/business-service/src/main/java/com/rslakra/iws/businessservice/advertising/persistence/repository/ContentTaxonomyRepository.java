package com.rslakra.iws.businessservice.advertising.persistence.repository;

import com.rslakra.frameworks.spring.persistence.repository.BaseRepository;
import com.rslakra.iws.businessservice.advertising.persistence.entity.ContentTaxonomy;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 2/7/23 2:25 PM
 */
@Repository
public interface ContentTaxonomyRepository extends BaseRepository<ContentTaxonomy, Long> {

    /**
     * @param parentId
     * @return
     */
    List<ContentTaxonomy> findByParentId(@Param("parentId") Long parentId);

}
