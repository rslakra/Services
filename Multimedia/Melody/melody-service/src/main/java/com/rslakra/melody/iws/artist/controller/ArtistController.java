package com.rslakra.melody.iws.artist.controller;

import com.devamatre.framework.core.Payload;
import com.devamatre.framework.spring.controller.rest.AbstractRestController;
import com.devamatre.framework.spring.filter.Filter;
import com.rslakra.melody.iws.artist.filter.ArtistFilter;
import com.rslakra.melody.iws.artist.persistence.entity.Artist;
import com.rslakra.melody.iws.artist.service.ArtistService;
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

/**
 * @author Rohtash Lakra (rlakra)
 * @created 5/25/22 5:08 PM
 */
@RestController
@RequestMapping("${restPrefix}/artists")
public class ArtistController extends AbstractRestController<Artist, Long> {

    private final ArtistService artistService;

    /**
     * @param artistService
     */
    @Autowired
    public ArtistController(final ArtistService artistService) {
        this.artistService = artistService;
    }

    /**
     * Returns the list of <code>T</code> objects.
     *
     * @return
     */
    @GetMapping
    @Override
    public List<Artist> getAll() {
        return artistService.getAll();
    }

    /**
     * Returns the list of <code>T</code> filters objects.
     *
     * @param allParams
     * @return
     */
    @GetMapping("/filter")
    @Override
    public List<Artist> getByFilter(@RequestParam Map<String, Object> allParams) {
        List<Artist> artists = Collections.emptyList();
        ArtistFilter artistFilter = new ArtistFilter(allParams);
        if (artistFilter.hasKeys(ArtistFilter.ID, ArtistFilter.FIRST_NAME)) {
        } else if (artistFilter.hasKey(ArtistFilter.ID)) {
            artists = Arrays.asList(artistService.getById(artistFilter.getLong(ArtistFilter.ID)));
        } else if (artistFilter.hasKey(ArtistFilter.FIRST_NAME)) {
        }

        return artists;
    }

    /**
     * Returns the <code>Page<T></code> list of objects filtered with <code>allParams</code>.
     *
     * @param allParams @return
     * @param pageable
     */
    @GetMapping("/pageable")
    @Override
    public Page<Artist> getByFilter(Map<String, Object> allParams, Pageable pageable) {
        return artistService.getByFilter(null, pageable);
    }

    /**
     * @param filter
     * @return
     */
    @Override
    public List<Artist> getByFilter(Filter filter) {
        return null;
    }

    /**
     * @param filter
     * @param pageable
     * @return
     */
    @Override
    public Page<Artist> getByFilter(Filter filter, Pageable pageable) {
        return null;
    }

    /**
     * Creates the <code>T</code> type object.
     *
     * @param artist
     * @return
     */
    @PostMapping
    @ResponseBody
    @Override
    public ResponseEntity<Artist> create(@Validated @RequestBody Artist artist) {
        artist = artistService.create(artist);
        return ResponseEntity.ok(artist);
    }

    /**
     * Creates the list of <code>T</code> type objects.
     *
     * @param artists
     * @return
     */
    @PostMapping("/batch")
    @ResponseBody
    @Override
    public ResponseEntity<List<Artist>> create(@Validated @RequestBody List<Artist> artists) {
        artists = artistService.create(artists);
        return ResponseEntity.ok(artists);
    }

    /**
     * Updates the <code>T</code> type object.
     *
     * @param artist
     * @return
     */
    @PutMapping
    @Override
    public ResponseEntity<Artist> update(@Validated @RequestBody Artist artist) {
        artist = artistService.update(artist);
        return ResponseEntity.ok(artist);
    }

    /**
     * Updates the list of <code>T</code> type objects.
     *
     * @param artists
     * @return
     */
    @PutMapping("/batch")
    @Override
    public ResponseEntity<List<Artist>> update(@Validated @RequestBody List<Artist> artists) {
        artists = artistService.update(artists);
        return ResponseEntity.ok(artists);
    }

    /**
     * Deletes the <code>T</code> type object by <code>id</code>.
     *
     * @param idOptional
     * @return
     */
    @DeleteMapping("/{artistId}")
    @Override
    public ResponseEntity<Payload> delete(@PathVariable(value = "artistId") Optional<Long> idOptional) {
        validate(idOptional);
        artistService.delete(idOptional.get());
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
