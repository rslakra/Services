package com.rslakra.melody.ews.contact.controller.web;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.core.Payload;
import com.devamatre.framework.spring.controller.web.AbstractWebController;
import com.devamatre.framework.spring.controller.web.WebController;
import com.devamatre.framework.spring.exception.InvalidRequestException;
import com.devamatre.framework.spring.filter.Filter;
import com.devamatre.framework.spring.parser.Parser;
import com.devamatre.framework.spring.parser.csv.CsvParser;
import com.devamatre.framework.spring.parser.excel.ExcelParser;
import com.rslakra.melody.ews.contact.payload.ContactUs;
import com.rslakra.melody.ews.contact.service.ContactUsService;
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
@RequestMapping("/contact-us")
public class ContactUsWebController extends AbstractWebController<ContactUs, Long>
    implements WebController<ContactUs, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactUsWebController.class);

    // contactUsService
    private final ContactUsService contactUsService;

    /**
     * @param contactUsService
     */
    @Autowired
    public ContactUsWebController(ContactUsService contactUsService) {
        this.contactUsService = contactUsService;
    }

    /**
     * Saves the <code>t</code> object.
     *
     * @param contactUs
     * @return
     */
    @PostMapping("/save")
    @Override
    public String save(ContactUs contactUs) {
        if (BeanUtils.isNotNull(contactUs.getId())) {
            ContactUs oldContactUs = contactUsService.getById(contactUs.getId());
            if (!oldContactUs.getEmail().equals(contactUs.getEmail())) {
                throw new InvalidRequestException();
            }
            BeanUtils.copyProperties(contactUs, oldContactUs);
            contactUs = contactUsService.update(oldContactUs);
        } else {
            contactUs = contactUsService.create(contactUs);
        }

        return "redirect:/contact-us";
    }

    /**
     * Returns the list of <code>T</code> objects.
     *
     * @param model
     * @return
     */
    @Override
    public String getAll(Model model) {
        return null;
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
    @GetMapping
    @Override
    public String editObject(Model model, @PathVariable(name = "id") Long id) {
        ContactUs contactUs = null;
        if (BeanUtils.isNotNull(id)) {
            contactUs = contactUsService.getById(id);
        } else {
            contactUs = new ContactUs();
        }
        model.addAttribute("contactUs", contactUs);

        return "views/contact-us";
    }

    /**
     * Deletes the object with <code>id</code>.
     *
     * @param model
     * @param id
     * @return
     */
    @Override
    public String delete(Model model, @PathVariable(name = "id") Long id) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public Parser<ContactUs> getParser() {
        return null;
    }

    /**
     * Uploads the file of <code>Artists</code>.
     *
     * @param file
     * @return
     */
//    @PostMapping("/upload")
    public ResponseEntity<Payload> upload(@RequestParam("file") MultipartFile file) {
        Payload payload = Payload.newBuilder();
        try {
            List<ContactUs> contactUss = null;
            if (CsvParser.isCSVFile(file)) {
                contactUss = ((CsvParser) getParser()).readCSVStream(file.getInputStream());
            } else if (ExcelParser.isExcelFile(file)) {
                contactUss = ((ExcelParser) getParser()).readStream(file.getInputStream());
            }

            // check the task list is available
            if (Objects.nonNull(contactUss)) {
                contactUss = contactUsService.create(contactUss);
                LOGGER.debug("contactUss: {}", contactUss);
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
//    @GetMapping("/download")
    public ResponseEntity<Resource> download(@RequestParam("fileType") String fileType) {
        BeanUtils.assertNonNull(fileType, "Download 'fileType' must provide!");
        ResponseEntity responseEntity = null;
        InputStreamResource inputStreamResource = null;
        String contentDisposition;
        MediaType mediaType;
        if (CsvParser.isCSVFileType(fileType)) {
//            contentDisposition = Parser.getContentDisposition(ArtistParser.CSV_DOWNLOAD_FILE_NAME);
            mediaType = Parser.getMediaType(CsvParser.CSV_MEDIA_TYPE);
            inputStreamResource = ((CsvParser) getParser()).buildCSVResourceStream(contactUsService.getAll());
        } else if (ExcelParser.isExcelFileType(fileType)) {
//            contentDisposition = Parser.getContentDisposition(ArtistParser.EXCEL_DOWNLOAD_FILE_NAME);
            mediaType = Parser.getMediaType(ExcelParser.EXCEL_MEDIA_TYPE);
            inputStreamResource = ((ExcelParser) getParser()).buildStreamResources(contactUsService.getAll());
        } else {
            throw new UnsupportedOperationException("Unsupported fileType:" + fileType);
        }

        // check inputStreamResource is not null
        if (Objects.nonNull(inputStreamResource)) {
//            responseEntity = Parser.buildOKResponse(contentDisposition, mediaType, inputStreamResource);
        }

        return responseEntity;
    }
}
