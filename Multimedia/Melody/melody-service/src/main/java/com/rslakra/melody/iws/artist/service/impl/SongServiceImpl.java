package com.rslakra.melody.iws.artist.service.impl;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.spring.exception.InvalidRequestException;
import com.devamatre.framework.spring.exception.NoRecordFoundException;
import com.devamatre.framework.spring.filter.Filter;
import com.devamatre.framework.spring.persistence.Operation;
import com.devamatre.framework.spring.service.AbstractServiceImpl;
import com.rslakra.melody.iws.artist.persistence.entity.Song;
import com.rslakra.melody.iws.artist.persistence.repository.ArtistRepository;
import com.rslakra.melody.iws.artist.persistence.repository.SongRepository;
import com.rslakra.melody.iws.artist.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 2/8/23 10:17 AM
 */
@Service
public class SongServiceImpl extends AbstractServiceImpl<Song, Long> implements SongService {

    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;

    /**
     * @param artistRepository
     * @param songRepository
     */
    @Autowired
    public SongServiceImpl(final ArtistRepository artistRepository, final SongRepository songRepository) {
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
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
                if (BeanUtils.isNull(song.getArtistId())) {
                    throw new InvalidRequestException("The song's artistId should provide!");
                } else if (BeanUtils.isEmpty(song.getTitle())) {
                    throw new InvalidRequestException("The song's title should provide!");
                }

                // check song exists for this artist
                if (!artistRepository.findById(song.getArtistId()).isPresent()) {
                    throw new NoRecordFoundException("artistId:%d", song.getArtistId());
                }

                break;

            case UPDATE:
                if (BeanUtils.isNull(song.getId())) {
                    throw new InvalidRequestException("The song's ID should provide!");
                } else if (BeanUtils.isNotNull(song.getArtistId())) {
                    // check song exists for this artist
                    if (!artistRepository.findById(song.getArtistId()).isPresent()) {
                        throw new NoRecordFoundException("artistId:%d", song.getArtistId());
                    }
                }

                // check song exists with this title
                if (songRepository.existsByTitle(song.getId(), song.getTitle())) {
                    throw new NoRecordFoundException("songId:%d", song.getId());
                }

                break;

            default:
                throw new InvalidRequestException("Unsupported Operation!");
        }

        return song;
    }


    /**
     * Creates the <code>T</code> object.
     *
     * @param song
     * @return
     */
    @Override
    public Song create(Song song) {
        validate(Operation.CREATE, song);
        song = songRepository.save(song);
        return song;
    }

    /**
     * Creates the <code>List<T></code> objects.
     *
     * @param songs
     * @return
     */
    @Override
    public List<Song> create(List<Song> songs) {
        if (BeanUtils.isEmpty(songs)) {
            throw new InvalidRequestException("The songs should provide!");
        }

        songs.forEach(song -> validate(Operation.CREATE, song));
        songs = songRepository.saveAll(songs);
        return songs;
    }

    /**
     * Returns the list of all <code>T</code> objects.
     *
     * @return
     */
    @Override
    public List<Song> getAll() {
        return songRepository.findAll();
    }

    /**
     * Returns the <code>T</code> object by <code>id</code>.
     *
     * @param id
     * @return
     */
    @Override
    public Song getById(final Long id) {
        return songRepository.findById(id).orElseThrow(() -> new NoRecordFoundException("id: %d", id));
    }

    /**
     * Returns the pageable <code>T</code> object by <code>pageable</code> filter.
     *
     * @param filter
     * @return
     */
    @Override
    public List<Song> getByFilter(Filter filter) {
        return songRepository.findAll();
    }

    /**
     * Returns the pageable <code>T</code> object by <code>pageable</code> filter.
     *
     * @param filter
     * @param pageable
     * @return
     */
    @Override
    public Page<Song> getByFilter(Filter filter, Pageable pageable) {
        return songRepository.findAll(pageable);
    }

    /**
     * Updates the <code>T</code> object.
     *
     * @param song
     * @return
     */
    @Override
    public Song update(Song song) {
        if (BeanUtils.isEmpty(song)) {
            throw new InvalidRequestException("The songs should provide!");
        }

        validate(Operation.UPDATE, song);
        song = songRepository.save(song);

        return song;
    }

    /**
     * Updates the <code>List<T></code> objects.
     *
     * @param songs
     * @return
     */
    @Override
    public List<Song> update(List<Song> songs) {
        if (BeanUtils.isEmpty(songs)) {
            throw new InvalidRequestException("The songs should provide!");
        }

        songs.forEach(song -> validate(Operation.UPDATE, song));
        songs = songRepository.saveAll(songs);
        return songs;
    }

    /**
     * Deletes the object by <code>id</code>.
     *
     * @param id
     * @return
     */
    @Override
    public Song delete(Long id) {
        Song song = getById(id);
        songRepository.delete(song);
        return song;
    }
}
