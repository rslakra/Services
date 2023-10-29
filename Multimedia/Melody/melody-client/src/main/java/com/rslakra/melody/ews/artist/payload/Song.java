package com.rslakra.melody.ews.artist.payload;

import com.devamatre.framework.spring.payload.dto.AbstractEntityDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 5/25/22 4:58 PM
 */

@Getter
@Setter
@NoArgsConstructor
public class Song extends AbstractEntityDTO<Long> {

    private Long artistId;
    private String title;
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
