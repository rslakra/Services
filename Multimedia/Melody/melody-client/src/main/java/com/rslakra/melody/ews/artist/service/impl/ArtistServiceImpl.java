package com.rslakra.melody.ews.artist.service.impl;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.core.Payload;
import com.devamatre.framework.spring.client.ApiRestClient;
import com.devamatre.framework.spring.exception.InvalidRequestException;
import com.devamatre.framework.spring.persistence.Operation;
import com.rslakra.melody.ews.account.payload.dto.Role;
import com.rslakra.melody.ews.artist.payload.Artist;
import com.rslakra.melody.ews.artist.service.ArtistService;
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
 * @created 2/8/23 10:06 AM
 */
@Service
public class ArtistServiceImpl extends AbstractClientServiceImpl<Artist> implements ArtistService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArtistServiceImpl.class);

    // apiRestClient
    private final ApiRestClient apiRestClient;

    /**
     * @param apiRestClient
     */
    @Autowired
    public ArtistServiceImpl(final ApiRestClient apiRestClient) {
        LOGGER.debug("ArtistServiceImpl({})", apiRestClient);
        this.apiRestClient = apiRestClient;
    }

    /**
     * Validates the <code>T</code> object.
     *
     * @param operation
     * @param artist
     * @return
     */
    @Override
    public Artist validate(Operation operation, Artist artist) {
        switch (operation) {
            case CREATE:
                if (BeanUtils.isEmpty(artist.getEmail())) {
                    throw new InvalidRequestException("The artist's email should provide!");
                } else if (BeanUtils.isEmpty(artist.getFirstName())) {
                    throw new InvalidRequestException("The artist's firstName should provide!");
                } else if (BeanUtils.isEmpty(artist.getLastName())) {
                    throw new InvalidRequestException("The artist's lastName should provide!");
                }

                break;

            case UPDATE:
                if (BeanUtils.isNull(artist.getId())) {
                    throw new InvalidRequestException("The artist's ID should provide!");
                }

                break;

            default:
                throw new InvalidRequestException("Unsupported Operation!");
        }

        return artist;
    }

    /**
     * @param artist
     * @return
     */
    @Override
    public Artist create(Artist artist) {
        LOGGER.debug("+create({})", artist);
        if (BeanUtils.isNull(artist)) {
            throw new InvalidRequestException("The artist should provide!");
        }

        validate(Operation.CREATE, artist);
        artist = apiRestClient.doPost(ARTISTS, artist, Artist.class);
        LOGGER.debug("-create(), artist: {}", artist);
        return artist;
    }

    /**
     * @param artists
     * @return
     */
    @Override
    public List<Artist> create(List<Artist> artists) {
        LOGGER.debug("+create({})", artists);
        if (BeanUtils.isEmpty(artists)) {
            throw new InvalidRequestException("The artists should provide!");
        }

        artists.forEach(artist -> validate(Operation.CREATE, artist));
        artists = apiRestClient.doPost(ARTISTS_BATCH, artists, List.class);
        LOGGER.debug("-create(), artists:{}", artists);
        return artists;
    }

    /**
     * @return
     */
    @Override
    public List<Artist> getAll() {
        LOGGER.debug("+getAll()");
        // note: get results by array and convert to list.
        List<Artist> artists;
        // helps to display empty ui page.
        try {
            artists = Arrays.asList(apiRestClient.doGet(ARTISTS, Artist[].class));
        } catch (Exception ex) {
            LOGGER.error(ex.getLocalizedMessage(), ex);
            // helps to display empty ui page.
            artists = new ArrayList<>();
        }

        LOGGER.debug("-getAll(), artists:{}", artists);
        return artists;
    }

    /**
     * @param filters
     * @return
     */
    @Override
    public List<Artist> getByFilter(Map<String, Object> filters) {
        LOGGER.debug("+getByFilter({})", filters);
        // rest/artists/filter?id=3
        final Artist[] artists = apiRestClient.doGet(ARTISTS_FILTER, Artist[].class, filters);
        LOGGER.debug("-getByFilter(), artists:{}", artists);
        return Arrays.asList(artists);
    }

    /**
     * @param filters
     * @param pageable
     * @return
     */
    @Override
    public List<Artist> getByFilter(Map<String, Object> filters, Pageable pageable) {
        LOGGER.debug("+getByFilter({}, {})", filters, pageable);
        List<Artist> artists = getByFilter(filters);
        LOGGER.debug("-getByFilter(), artists:{}", artists);
        return artists;
    }

    /**
     * Returns the <code>T</code> object by <code>id</code>.
     *
     * @param id
     * @return
     */
    @Override
    public Artist getById(Long id) {
        LOGGER.debug("+getById({})", id);
        // rest/artists/filter?id=16
        Artist artist = (Artist) getByFilter(Payload.newBuilder().ofPair("id", id)).stream().findFirst().orElse(null);
        LOGGER.debug("-getById(), artist:{}", artist);
        return artist;
    }

    /**
     * @param artist
     * @return
     */
    @Override
    public Artist update(Artist artist) {
        LOGGER.debug("+update({})", artist);
        if (BeanUtils.isEmpty(artist)) {
            throw new InvalidRequestException("The artist should provide!");
        }

        validate(Operation.UPDATE, artist);
        apiRestClient.doPut(ARTISTS, artist, Role.class);

        LOGGER.debug("-update(), artist:{}", artist);
        return artist;
    }

    /**
     * @param artists
     * @return
     */
    @Override
    public List<Artist> update(List<Artist> artists) {
        LOGGER.debug("+update({})", artists);
        if (BeanUtils.isEmpty(artists)) {
            throw new InvalidRequestException("The artists should provide!");
        }

        artists.forEach(artist -> validate(Operation.UPDATE, artist));
        apiRestClient.doPut(ARTISTS_BATCH, artists, List.class);

        LOGGER.debug("-update(), artists:{}", artists);
        return artists;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Artist delete(Long id) {
        LOGGER.debug("+delete({})", id);
        BeanUtils.assertNonNull(id, "The artist's id should provide!");
        Artist artist = null;
        apiRestClient.doDelete(ARTIST_BY_ID, Payload.newBuilder().ofPair("id", id));
        LOGGER.debug("-delete(), artist:{}", artist);
        return artist;
    }
}
