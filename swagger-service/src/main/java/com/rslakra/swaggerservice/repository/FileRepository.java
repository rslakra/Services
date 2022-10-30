package com.rslakra.swaggerservice.repository;

import com.rslakra.swaggerservice.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 8/4/21 5:57 PM
 */
@Repository
public interface FileRepository extends JpaRepository<File, Long> {

}
