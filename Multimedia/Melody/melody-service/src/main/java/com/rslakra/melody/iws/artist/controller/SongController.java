package com.rslakra.melody.iws.artist.controller;

import com.devamatre.framework.core.Payload;
import com.devamatre.framework.spring.controller.rest.AbstractRestController;
import com.devamatre.framework.spring.filter.Filter;
import com.rslakra.melody.iws.artist.filter.SongFilter;
import com.rslakra.melody.iws.artist.persistence.entity.Song;
import com.rslakra.melody.iws.artist.service.ArtistService;
import com.rslakra.melody.iws.artist.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 5/25/22 5:08 PM
 */
@RestController
@RequestMapping("${restPrefix}/songs")
public class SongController extends AbstractRestController<Song, Long> {

    private final ArtistService artistService;
    private final SongService songService;

    /**
     * @param artistService
     * @param songService
     */
    @Autowired
    public SongController(final ArtistService artistService, final SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    /**
     * Returns the list of <code>T</code> objects.
     *
     * @return
     */
    @GetMapping
    @Override
    public List<Song> getAll() {
        return songService.getAll();
    }

    /**
     * Returns the list of <code>T</code> filters objects.
     *
     * @param allParams
     * @return
     */
    @GetMapping("/filter")
    @Override
    public List<Song> getByFilter(@RequestParam Map<String, Object> allParams) {
        List<Song> songs = Collections.emptyList();
        SongFilter songFilter = new SongFilter(allParams);
        if (songFilter.hasKeys(SongFilter.ID, SongFilter.ARTIST_ID, SongFilter.TITLE)) {
        } else if (songFilter.hasKey(SongFilter.ID)) {
            songs = Arrays.asList(songService.getById(songFilter.getLong(SongFilter.ID)));
        } else if (songFilter.hasKey(SongFilter.ARTIST_ID)) {
            final Long artistId = songFilter.getLong(SongFilter.ARTIST_ID);
            songs =
                songService.getAll().stream()
                    .filter(song -> song.getArtistId() != null)
                    .filter(song -> song.getArtistId().equals(artistId))
                    .collect(Collectors.toList());
        } else if (songFilter.hasKey(SongFilter.TITLE)) {
        }

        return songs;
    }

    /**
     * Returns the <code>Page<T></code> list of objects filtered with <code>allParams</code>.
     *
     * @param allParams @return
     * @param pageable
     */
    @GetMapping("/pageable")
    @Override
    public Page<Song> getByFilter(Map<String, Object> allParams, Pageable pageable) {
        return songService.getByFilter(null, pageable);
    }

    /**
     * @param filter
     * @return
     */
    @Override
    public List<Song> getByFilter(Filter filter) {
        return null;
    }

    /**
     * @param filter
     * @param pageable
     * @return
     */
    @Override
    public Page<Song> getByFilter(Filter filter, Pageable pageable) {
        return null;
    }

    /**
     * Creates the <code>T</code> type object.
     *
     * @param role
     * @return
     */
    @PostMapping
    @ResponseBody
    @Override
    public ResponseEntity<Song> create(@Validated @RequestBody Song role) {
        role = songService.create(role);
        return ResponseEntity.ok(role);
    }

    /**
     * Creates the list of <code>T</code> type objects.
     *
     * @param songs
     * @return
     */
    @PostMapping("/batch")
    @ResponseBody
    @Override
    public ResponseEntity<List<Song>> create(@Validated @RequestBody List<Song> songs) {
        songs = songService.create(songs);
        return ResponseEntity.ok(songs);
    }

    /**
     * Updates the <code>T</code> type object.
     *
     * @param role
     * @return
     */
    @PutMapping
    @Override
    public ResponseEntity<Song> update(@Validated @RequestBody Song role) {
        role = songService.update(role);
        return ResponseEntity.ok(role);
    }

    /**
     * Updates the list of <code>T</code> type objects.
     *
     * @param songs
     * @return
     */
    @PutMapping("/batch")
    @Override
    public ResponseEntity<List<Song>> update(@Validated @RequestBody List<Song> songs) {
        songs = songService.update(songs);
        return ResponseEntity.ok(songs);
    }

    /**
     * Deletes the <code>T</code> type object by <code>id</code>.
     *
     * @param idOptional
     * @return
     */
    @DeleteMapping("/{songId}")
    @Override
    public ResponseEntity<Payload> delete(@PathVariable(value = "songId") Optional<Long> idOptional) {
        validate(idOptional);
        songService.delete(idOptional.get());
        Payload payload = Payload.newBuilder()
            .withDeleted(Boolean.TRUE)
            .withMessage("Record with id:%d deleted successfully!", idOptional.get());
        return ResponseEntity.ok(payload);
    }

    /**
     * Uploads the <code>file</code>
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @Override
    public ResponseEntity<Payload> upload(MultipartFile file) {
        return null;
    }

    /**
     * Downloads the object of <code>T</code> as <code>fileType</code> file.
     *
     * @param fileType
     * @return
     */
    @GetMapping("/download")
    @Override
    public ResponseEntity<Resource> download(String fileType) {
        return null;
    }

}
