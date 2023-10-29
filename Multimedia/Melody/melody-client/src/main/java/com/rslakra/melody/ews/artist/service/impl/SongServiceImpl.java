package com.rslakra.melody.ews.artist.service.impl;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.core.Payload;
import com.devamatre.framework.spring.client.ApiRestClient;
import com.devamatre.framework.spring.exception.InvalidRequestException;
import com.devamatre.framework.spring.persistence.Operation;
import com.rslakra.melody.ews.account.payload.dto.Role;
import com.rslakra.melody.ews.artist.payload.Song;
import com.rslakra.melody.ews.artist.service.SongService;
import com.rslakra.melody.ews.framework.client.impl.AbstractClientServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Rohtash Lakra
 * @created 2/8/23 10:17 AM
 */
@Service
public class SongServiceImpl extends AbstractClientServiceImpl<Song> implements SongService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SongServiceImpl.class);

    // apiRestClient
    private final ApiRestClient apiRestClient;

    /**
     * @param apiRestClient
     */
    @Autowired
    public SongServiceImpl(final ApiRestClient apiRestClient) {
        LOGGER.debug("SongServiceImpl({})", apiRestClient);
        this.apiRestClient = apiRestClient;
    }

    /**
     * Validates the <code>T</code> object.
     *
     * @param operation
     * @param song
     * @return
     */
    @Override
    public Song validate(Operation operation, Song song) {
        if (BeanUtils.isNull(song)) {
            throw new InvalidRequestException("The song should provide!");
        }

        switch (operation) {
            case CREATE:
                if (BeanUtils.isNull(song.getId())) {
                    throw new InvalidRequestException("The song's ID should provide!");
                } else if (BeanUtils.isEmpty(song.getTitle())) {
                    throw new InvalidRequestException("The song's title should provide!");
                }

                break;

            case UPDATE:
                if (BeanUtils.isNull(song.getId())) {
                    throw new InvalidRequestException("The song's ID should provide!");
                }

                break;

            default:
                throw new InvalidRequestException("Unsupported Operation!");
        }

        return song;
    }


    /**
     * @param song
     * @return
     */
    @Override
    public Song create(Song song) {
        LOGGER.debug("+create({})", song);
        if (BeanUtils.isNull(song)) {
            throw new InvalidRequestException("The song should provide!");
        }

        validate(Operation.CREATE, song);
        song = apiRestClient.doPost(SONGS, song, Song.class);
        LOGGER.debug("-create(), song: {}", song);
        return song;
    }

    /**
     * @param songs
     * @return
     */
    @Override
    public List<Song> create(List<Song> songs) {
        LOGGER.debug("+create({})", songs);
        if (BeanUtils.isEmpty(songs)) {
            throw new InvalidRequestException("The songs should provide!");
        }

        songs.forEach(song -> validate(Operation.CREATE, song));
        songs = apiRestClient.doPost(SONGS_BATCH, songs, List.class);
        LOGGER.debug("-create(), songs:{}", songs);
        return songs;
    }

    /**
     * @return
     */
    @Override
    public List<Song> getAll() {
        LOGGER.debug("+getAll()");
        // note: get results by array and convert to list.
        List<Song> songs;
        // helps to display empty ui page.
        try {
            songs = Arrays.asList(apiRestClient.doGet(SONGS, Song[].class));
        } catch (Exception ex) {
            LOGGER.error(ex.getLocalizedMessage(), ex);
            // helps to display empty ui page.
            songs = new ArrayList<>();
        }

        LOGGER.debug("-getAll(), songs:{}", songs);
        return songs;
    }

    /**
     * @param filters
     * @return
     */
    @Override
    public List<Song> getByFilter(Map<String, Object> filters) {
        LOGGER.debug("+getByFilter({})", filters);
        // rest/songs/filter?id=3
        final Song[] songs = apiRestClient.doGet(SONGS_FILTER, Song[].class, filters);
        LOGGER.debug("-getByFilter(), songs:{}", songs);
        return Arrays.asList(songs);
    }

    /**
     * @param filters
     * @param pageable
     * @return
     */
    @Override
    public List<Song> getByFilter(Map<String, Object> filters, Pageable pageable) {
        LOGGER.debug("+getByFilter({}, {})", filters, pageable);
        List<Song> songs = getByFilter(filters);
        LOGGER.debug("-getByFilter(), songs:{}", songs);
        return songs;
    }

    /**
     * Returns the <code>T</code> object by <code>id</code>.
     *
     * @param id
     * @return
     */
    @Override
    public Song getById(Long id) {
        LOGGER.debug("+getById({})", id);
        // rest/songs/filter?id=16
        Song song = (Song) getByFilter(Payload.newBuilder().ofPair("id", id)).stream().findFirst().orElse(null);
        LOGGER.debug("-getById(), song:{}", song);
        return song;
    }

    /**
     * @param song
     * @return
     */
    @Override
    public Song update(Song song) {
        LOGGER.debug("+update({})", song);
        if (BeanUtils.isEmpty(song)) {
            throw new InvalidRequestException("The song should provide!");
        }

        validate(Operation.UPDATE, song);
        apiRestClient.doPut(SONGS, song, Role.class);

        LOGGER.debug("-update(), song:{}", song);
        return song;
    }

    /**
     * @param songs
     * @return
     */
    @Override
    public List<Song> update(List<Song> songs) {
        LOGGER.debug("+update({})", songs);
        if (BeanUtils.isEmpty(songs)) {
            throw new InvalidRequestException("The songs should provide!");
        }

        songs.forEach(song -> validate(Operation.UPDATE, song));
        apiRestClient.doPut(SONGS_BATCH, songs, List.class);

        LOGGER.debug("-update(), songs:{}", songs);
        return songs;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Song delete(Long id) {
        LOGGER.debug("+delete({})", id);
        BeanUtils.assertNonNull(id, "The song's id should provide!");
        Song song = null;
        apiRestClient.doDelete(SONG_BY_ID, Payload.newBuilder().ofPair("id", id));
        LOGGER.debug("-delete(), song:{}", song);
        return song;
    }

}
