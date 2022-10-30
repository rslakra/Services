package com.rslakra.auditingservice.repository;

import com.rslakra.auditingservice.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 8/4/21 5:57 PM
 */
public interface FileRepository extends JpaRepository<File, Long> {

}
