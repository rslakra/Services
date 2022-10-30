package com.rslakra.swaggerservice.persistence.repository;

import com.rslakra.swaggerservice.persistence.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Rohtash Lakra
 * @created 8/4/21 5:57 PM
 */
@Repository
public interface FileRepository extends JpaRepository<File, Long> {

}
