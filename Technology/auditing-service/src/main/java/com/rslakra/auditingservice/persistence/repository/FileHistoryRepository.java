package com.rslakra.auditingservice.persistence.repository;

import com.rslakra.auditingservice.persistence.entity.FileHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Rohtash Lakra
 * @created 8/4/21 5:58 PM
 */
public interface FileHistoryRepository extends JpaRepository<FileHistory, Long> {

}
