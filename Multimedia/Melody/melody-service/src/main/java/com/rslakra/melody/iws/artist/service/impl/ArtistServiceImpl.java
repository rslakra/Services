package com.rslakra.melody.iws.artist.service.impl;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.spring.exception.DuplicateRecordException;
import com.devamatre.framework.spring.exception.InvalidRequestException;
import com.devamatre.framework.spring.exception.NoRecordFoundException;
import com.devamatre.framework.spring.filter.Filter;
import com.devamatre.framework.spring.persistence.Operation;
import com.devamatre.framework.spring.service.AbstractServiceImpl;
import com.rslakra.melody.iws.artist.persistence.entity.Artist;
import com.rslakra.melody.iws.artist.persistence.repository.ArtistRepository;
import com.rslakra.melody.iws.artist.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 2/8/23 10:06 AM
 */
@Service
public class ArtistServiceImpl extends AbstractServiceImpl<Artist, Long> implements ArtistService {

    private final ArtistRepository artistRepository;

    /**
     * @param artistRepository
     */
    @Autowired
    public ArtistServiceImpl(final ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
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

                // check artist exists for this email
                if (artistRepository.findByEmail(artist.getEmail()).isPresent()) {
                    throw new DuplicateRecordException("artist");
                }

                break;

            case UPDATE:
                if (BeanUtils.isNull(artist.getId())) {
                    throw new InvalidRequestException("The artist's ID should provide!");
                }

                if (BeanUtils.isNotEmpty(artist.getEmail())) {
                    // check artist exists for this email
                    if (artistRepository.existsByEmail(artist.getId(), artist.getEmail())) {
                        throw new DuplicateRecordException("artist email:%s", artist.getEmail());
                    }
                }

                break;

            default:
                throw new InvalidRequestException("Unsupported Operation!");
        }

        return artist;
    }

    /**
     * Creates the <code>T</code> object.
     *
     * @param artist
     * @return
     */
    @Override
    public Artist create(Artist artist) {
        validate(Operation.CREATE, artist);
        artist = artistRepository.save(artist);
        return artist;
    }

    /**
     * Creates the <code>List<T></code> objects.
     *
     * @param artists
     * @return
     */
    @Override
    public List<Artist> create(List<Artist> artists) {
        if (BeanUtils.isEmpty(artists)) {
            throw new InvalidRequestException("The artists should provide!");
        }

        artists.forEach(artist -> validate(Operation.CREATE, artist));
        artists = artistRepository.saveAll(artists);

        return artists;
    }

    /**
     * Returns the list of all <code>T</code> objects.
     *
     * @return
     */
    @Override
    public List<Artist> getAll() {
        return artistRepository.findAll();
    }

    /**
     * Returns the <code>T</code> object by <code>id</code>.
     *
     * @param id
     * @return
     */
    @Override
    public Artist getById(final Long id) {
        return artistRepository.findById(id).orElseThrow(() -> new NoRecordFoundException("id:%d", id));
    }

    /**
     * Returns the pageable <code>T</code> object by <code>pageable</code> filter.
     *
     * @param filter
     * @return
     */
    @Override
    public List<Artist> getByFilter(Filter filter) {
        return artistRepository.findAll();
    }

    /**
     * Returns the pageable <code>T</code> object by <code>pageable</code> filter.
     *
     * @param filter
     * @param pageable
     * @return
     */
    @Override
    public Page<Artist> getByFilter(Filter filter, Pageable pageable) {
        return artistRepository.findAll(pageable);
    }

    /**
     * Updates the <code>T</code> object.
     *
     * @param artist
     * @return
     */
    @Override
    public Artist update(Artist artist) {
        validate(Operation.UPDATE, artist);
        artist = artistRepository.save(artist);
        return artist;
    }

    /**
     * Updates the <code>List<T></code> objects.
     *
     * @param artists
     * @return
     */
    @Override
    public List<Artist> update(List<Artist> artists) {
        if (BeanUtils.isEmpty(artists)) {
            throw new InvalidRequestException("The artists should provide!");
        }

        artists.forEach(artist -> validate(Operation.UPDATE, artist));
        artists = artistRepository.saveAll(artists);
        return artists;
    }

    /**
     * Deletes the object by <code>id</code>.
     *
     * @param id
     * @return
     */
    @Override
    public Artist delete(Long id) {
        Artist artist = getById(id);
        artistRepository.delete(artist);
        return artist;
    }
}
