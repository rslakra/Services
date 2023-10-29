package com.rslakra.melody.iws.artist.persistence.repository;

import com.devamatre.framework.spring.persistence.repository.BaseRepository;
import com.rslakra.melody.iws.artist.persistence.entity.Song;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Rohtash Lakra
 * @created 2/7/23 2:25 PM
 */
@Repository
public interface SongRepository extends BaseRepository<Song, Long> {

    Boolean existsByTitle(@Param("title") String title);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Song s WHERE s.id != :id AND s.title = :title")
    Boolean existsByTitle(@Param("id") Long id, @Param("title") String title);

}
