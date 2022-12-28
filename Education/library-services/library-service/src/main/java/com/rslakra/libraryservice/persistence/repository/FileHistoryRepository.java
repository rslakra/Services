package com.rslakra.libraryservice.persistence.repository;

import com.rslakra.libraryservice.persistence.entity.FileHistory;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 8/4/21 5:58 PM
 */
@Repository
public interface FileHistoryRepository extends BaseRepository<FileHistory, Long> {

    /**
     * @param fileId
     * @return
     */
    public List<FileHistory> getAllByFileId(@Param("fileId") Long fileId);
}
