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
import com.rslakra.melody.ews.artist.parser.ArtistParser;
import com.rslakra.melody.ews.artist.payload.Artist;
import com.rslakra.melody.ews.artist.service.ArtistService;
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
 * @author: Rohtash Lakra (rlakra)
 * @since: 9/30/19 5:38 PM
 */
@Controller
@RequestMapping("/artists")
public class ArtistWebController extends AbstractWebController<Artist, Long> implements WebController<Artist, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArtistWebController.class);

    private final ArtistParser artistParser;
    // artistService
    private final ArtistService artistService;

    /**
     * @param artistService
     */
    @Autowired
    public ArtistWebController(ArtistService artistService) {
        this.artistParser = new ArtistParser();
        this.artistService = artistService;
    }

    /**
     * Saves the <code>t</code> object.
     *
     * @param artist
     * @return
     */
    @PostMapping("/save")
    @Override
    public String save(Artist artist) {
        if (BeanUtils.isNotNull(artist.getId())) {
            Artist oldArtist = artistService.getById(artist.getId());
            if (!oldArtist.getEmail().equals(artist.getEmail())) {
                throw new InvalidRequestException();
            }
            BeanUtils.copyProperties(artist, oldArtist);
            artist = artistService.update(oldArtist);
        } else {
            artist = artistService.create(artist);
        }

        return "redirect:/artists/list";
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
        List<Artist> artists = artistService.getAll();
        model.addAttribute("artists", artists);

        return "views/artist/listArtists";
    }

    /**
     * Filters the list of <code>T</code> objects.
     *
     * @param model
     * @param filter
     * @return
     */
    @Override
    public String filter(Model model, Filter filter) {
        return null;
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
     * Create the new object or Updates the object with <code>id</code>.
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(path = {"/create", "/update/{id}"})
    @Override
    public String editObject(Model model, @PathVariable(name = "id") Long id) {
        Artist artist = null;
        if (BeanUtils.isNotNull(id)) {
            artist = artistService.getById(id);
        } else {
            artist = new Artist();
        }
        model.addAttribute("artist", artist);

        return "views/artist/editArtist";
    }


    /**
     * Deletes the object with <code>id</code>.
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    @Override
    public String delete(Model model, @PathVariable(name = "id") Long id) {
        artistService.delete(id);
        return "redirect:/artists/list";
    }

    /**
     * @return
     */
    @Override
    public Parser<Artist> getParser() {
        return artistParser;
    }

    /**
     * Displays the upload <code>Artists</code> UI.
     *
     * @return
     */
    @GetMapping(path = {"/upload"})
    public String showUploadPage() {
        return "views/artist/uploadArtists";
    }

    /**
     * Uploads the file of <code>Artists</code>.
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ResponseEntity<Payload> upload(@RequestParam("file") MultipartFile file) {
        Payload payload = Payload.newBuilder();
        try {
            List<Artist> artists = null;
            if (CsvParser.isCSVFile(file)) {
                artists = ((CsvParser) getParser()).readCSVStream(file.getInputStream());
            } else if (ExcelParser.isExcelFile(file)) {
                artists = ((ExcelParser) getParser()).readStream(file.getInputStream());
            }

            // check the task list is available
            if (Objects.nonNull(artists)) {
                artists = artistService.create(artists);
                LOGGER.debug("artists: {}", artists);
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
            contentDisposition = Parser.getContentDisposition(ArtistParser.CSV_DOWNLOAD_FILE_NAME);
            mediaType = Parser.getMediaType(CsvParser.CSV_MEDIA_TYPE);
            inputStreamResource = ((CsvParser) getParser()).buildCSVResourceStream(artistService.getAll());
        } else if (ExcelParser.isExcelFileType(fileType)) {
            contentDisposition = Parser.getContentDisposition(ArtistParser.EXCEL_DOWNLOAD_FILE_NAME);
            mediaType = Parser.getMediaType(ExcelParser.EXCEL_MEDIA_TYPE);
            inputStreamResource = ((ExcelParser) getParser()).buildStreamResources(artistService.getAll());
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
