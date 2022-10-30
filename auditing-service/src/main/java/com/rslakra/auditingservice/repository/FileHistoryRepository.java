package com.rslakra.auditingservice.repository;

import com.rslakra.auditingservice.entity.FileHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 8/4/21 5:58 PM
 */
public interface FileHistoryRepository extends JpaRepository<FileHistory, Long> {

}
