package com.rslakra.melody.ews.artist.service;

import com.rslakra.melody.ews.artist.payload.Artist;
import com.rslakra.melody.ews.framework.client.AbstractClientService;

/**
 * @author Rohtash Lakra
 * @created 2/8/23 9:51 AM
 */
public interface ArtistService extends AbstractClientService<Artist> {

    String ARTISTS = "artists";
    String ARTISTS_BATCH = "artists/batch";
    String ARTIST_BY_ID = "artists/{id}";
    String ARTISTS_FILTER = "artists/filter";

}
