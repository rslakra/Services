package com.rslakra.melody.iws.artist.persistence.repository;

import com.devamatre.framework.spring.persistence.repository.BaseRepository;
import com.rslakra.melody.iws.artist.persistence.entity.Artist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Rohtash Lakra
 * @created 2/7/23 2:25 PM
 */
@Repository
public interface ArtistRepository extends BaseRepository<Artist, Long> {

    Boolean existsByEmail(@Param("email") String email);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Artist a WHERE a.id != :id AND a.email = :email")
    Boolean existsByEmail(@Param("id") Long id, @Param("email") String email);

    /**
     * @param email
     * @return
     */
    Optional<Artist> findByEmail(@Param("email") String email);
}
