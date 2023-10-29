package com.rslakra.melody.iws.artist.persistence.entity;

import com.devamatre.framework.spring.persistence.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 5/25/22 4:58 PM
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "songs")
public class Song extends AbstractEntity<Long> {

    @Column(name = "artist_id")
    private Long artistId;

    @Column(name = "title")
    private String title;

    @Column(name = "rating")
    private Integer rating;

    /**
     * @param id
     * @param title
     * @param rating
     */
    public Song(Long id, String title, Integer rating) {
        setId(id);
        this.title = title;
        this.rating = rating;
    }

}
