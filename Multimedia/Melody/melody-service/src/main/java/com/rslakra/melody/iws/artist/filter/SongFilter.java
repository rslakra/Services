package com.rslakra.melody.iws.artist.filter;

import com.devamatre.framework.spring.filter.AbstractFilterImpl;

import java.util.Map;

/**
 * @author Rohtash Lakra
 * @created 2/8/23 1:06 PM
 */
public final class SongFilter extends AbstractFilterImpl {

    public static final String ARTIST_ID = "artistId";
    public static final String TITLE = "title";


    /**
     * @param allParams
     */
    public SongFilter(Map<String, Object> allParams) {
        super(allParams);
    }
}
