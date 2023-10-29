package com.rslakra.melody.ews.artist.controller.web;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.core.Payload;
import com.devamatre.framework.spring.controller.web.AbstractWebController;
import com.devamatre.framework.spring.controller.web.WebController;
import com.devamatre.framework.spring.exception.InvalidRequestException;
import com.devamatre.framework.spring.filter.Filter;
import com.devamatre.framework.spring.parser.Parser;
import com.devamatre.framework.spring.parser.csv.CsvParser;
import com.devamatre.framework.spring.parser.excel.ExcelParser;
import com.rslakra.melody.ews.artist.parser.SongParser;
import com.rslakra.melody.ews.artist.payload.Song;
import com.rslakra.melody.ews.artist.service.SongService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author: Rohtash Lakra
 * @since: 9/30/19 5:38 PM
 */
@Controller
@RequestMapping("/songs")
public class SongWebController extends AbstractWebController<Song, Long> implements WebController<Song, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SongWebController.class);

    private final SongParser songParser;
    // songService
    private final SongService songService;

    /**
     * @param songService
     */
    @Autowired
    public SongWebController(SongService songService) {
        this.songParser = new SongParser();
        this.songService = songService;
    }

    /**
     * Saves the <code>t</code> object.
     *
     * @param song
     * @return
     */
    @PostMapping("/save")
    @Override
    public String save(Song song) {
        if (BeanUtils.isNotNull(song.getId())) {
            Song oldSong = songService.getById(song.getId());
            if (!oldSong.getTitle().equals(song.getTitle())) {
                throw new InvalidRequestException();
            }
            BeanUtils.copyProperties(song, oldSong);
            song = songService.update(oldSong);
        } else {
            song = songService.create(song);
        }

        return "redirect:/songs/list";
    }

    /**
     * Returns the list of <code>T</code> objects.
     *
     * @param model
     * @return
     */
    @GetMapping("/list")
    @Override
    public String getAll(Model model) {
        List<Song> songs = songService.getAll();
        model.addAttribute("songs", songs);

        return "views/song/listSongs";
    }

    /**
     * Filters the list of <code>T</code> objects.
     *
     * @param model
     * @param filter
     * @return
     */
    @GetMapping("/filter")
    @Override
    public String filter(Model model, Filter filter) {
        List<Song> songs = songService.getAll();
        model.addAttribute("songs", songs);

        return "views/song/listSongs";
    }

    /**
     * @param model
     * @param allParams
     * @return
     */
    @Override
    public String filter(Model model, Map<String, Object> allParams) {
        return null;
    }

    /**
     * @param model
     * @param songId
     * @return
     */
    @RequestMapping(path = {"/create", "/update/{songId}"})
    public String editObject(Model model, @PathVariable(name = "songId") Long songId) {
        Song song = null;
        if (BeanUtils.isNotNull(song)) {
            song = songService.getById(songId);
        } else {
            song = new Song();
        }
        model.addAttribute("song", song);
        return "views/song/editSong";
    }

    /**
     * Deletes the object with <code>id</code>.
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/delete/{songId}")
    @Override
    public String delete(Model model, @PathVariable(name = "songId") Long id) {
        songService.delete(id);
        return "redirect:/songs/list";
    }

    /**
     * @return
     */
    @Override
    public Parser<Song> getParser() {
        return songParser;
    }

    /**
     * Displays the upload <code>Songs</code> UI.
     *
     * @return
     */
    @GetMapping(path = {"/upload"})
    public String showUploadPage() {
        return "views/song/uploadSongs";
    }

    /**
     * Uploads the file of <code>Songs</code>.
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ResponseEntity<Payload> upload(@RequestParam("file") MultipartFile file) {
        Payload payload = Payload.newBuilder();
        try {
            List<Song> songs = null;
            if (CsvParser.isCSVFile(file)) {
                songs = ((CsvParser) getParser()).readCSVStream(file.getInputStream());
            } else if (ExcelParser.isExcelFile(file)) {
                songs = ((ExcelParser) getParser()).readStream(file.getInputStream());
            }

            // check the task list is available
            if (Objects.nonNull(songs)) {
                songs = songService.create(songs);
                LOGGER.debug("songs: {}", songs);
                payload.withMessage("Uploaded the file '%s' successfully!", file.getOriginalFilename());
                return ResponseEntity.status(HttpStatus.OK).body(payload);
            }
        } catch (Exception ex) {
            LOGGER.error("Could not upload the file:{}!", file.getOriginalFilename(), ex);
            payload.withMessage("Could not upload the file '%s'!", file.getOriginalFilename());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(payload);
        }

        payload.withMessage("Unsupported file type!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(payload);
    }

    /**
     * Downloads the object of <code>T</code> as <code>fileType</code> file.
     *
     * @param fileType
     * @return
     */
    @GetMapping("/download")
    public ResponseEntity<Resource> download(@RequestParam("fileType") String fileType) {
        BeanUtils.assertNonNull(fileType, "Download 'fileType' must provide!");
        ResponseEntity responseEntity = null;
        InputStreamResource inputStreamResource = null;
        String contentDisposition;
        MediaType mediaType;
        if (CsvParser.isCSVFileType(fileType)) {
            contentDisposition = Parser.getContentDisposition(SongParser.CSV_DOWNLOAD_FILE_NAME);
            mediaType = Parser.getMediaType(CsvParser.CSV_MEDIA_TYPE);
            inputStreamResource = ((CsvParser) getParser()).buildCSVResourceStream(songService.getAll());
        } else if (ExcelParser.isExcelFileType(fileType)) {
            contentDisposition = Parser.getContentDisposition(SongParser.EXCEL_DOWNLOAD_FILE_NAME);
            mediaType = Parser.getMediaType(ExcelParser.EXCEL_MEDIA_TYPE);
            inputStreamResource = ((ExcelParser) getParser()).buildStreamResources(songService.getAll());
        } else {
            throw new UnsupportedOperationException("Unsupported fileType:" + fileType);
        }

        // check inputStreamResource is not null
        if (Objects.nonNull(inputStreamResource)) {
            responseEntity = Parser.buildOKResponse(contentDisposition, mediaType, inputStreamResource);
        }

        return responseEntity;
    }
}
