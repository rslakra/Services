package com.rslakra.libraryservice.persistence.repository;

import com.rslakra.libraryservice.persistence.entity.File;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Rohtash Lakra
 * @created 8/4/21 5:57 PM
 */
@Repository
public interface FileRepository extends BaseRepository<File, Long> {

    /**
     * @param name
     * @return
     */
    public Optional<File> getByName(@Param("name") String name);

    /**
     * @param contents
     * @return
     */
    public List<File> getByContents(@Param("contents") String contents);

}
