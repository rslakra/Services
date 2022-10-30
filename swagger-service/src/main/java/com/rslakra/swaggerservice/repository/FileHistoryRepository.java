package com.rslakra.swaggerservice.repository;

import com.rslakra.swaggerservice.entity.FileHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 8/4/21 5:58 PM
 */
@Repository
public interface FileHistoryRepository extends JpaRepository<FileHistory, Long> {

}
