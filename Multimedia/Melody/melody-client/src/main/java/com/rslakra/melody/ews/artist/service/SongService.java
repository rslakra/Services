package com.rslakra.melody.ews.artist.service;

import com.rslakra.melody.ews.artist.payload.Song;
import com.rslakra.melody.ews.framework.client.AbstractClientService;

/**
 * @author Rohtash Lakra
 * @created 2/8/23 9:49 AM
 */
public interface SongService extends AbstractClientService<Song> {

    String SONGS = "artists";
    String SONGS_BATCH = "artists/batch";
    String SONG_BY_ID = "artists/{id}";
    String SONGS_FILTER = "artists/filter";

}
