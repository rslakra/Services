package com.rslakra.melody.ews.artist.parser;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.spring.parser.Parser;
import com.devamatre.framework.spring.parser.csv.CsvParser;
import com.devamatre.framework.spring.parser.excel.ExcelParser;
import com.rslakra.melody.ews.artist.payload.Artist;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 4/5/23 4:55 PM
 */
public class ArtistParser implements ExcelParser<Artist>, CsvParser<Artist>, Parser<Artist> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArtistParser.class);

    public static final String CSV_DOWNLOAD_FILE_NAME = "artists.csv";
    public static final String EXCEL_DOWNLOAD_FILE_NAME = "artists.xlsx";
    public static final String[] HEADERS = {
        "id", "email", "firstName", "middleName", "lastName"
    };

    /**
     * @return
     */
    @Override
    public String getUploadFileName() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public String getDownloadFileName() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public String[] getReadHeaders() {
        return HEADERS;
    }

    /**
     * @return
     */
    @Override
    public String[] getWriteHeaders() {
        return HEADERS;
    }

    /**
     * @return
     */
    @Override
    public String getSheetName() {
        return Artist.class.getSimpleName();
    }

    /**
     * @param rowCells
     * @return
     */
    @Override
    public Artist readCells(Iterator<Cell> rowCells) {
        Artist artist = new Artist();
        int cellIndex = 0;
        while (rowCells.hasNext()) {
            Cell currentCell = rowCells.next();
            // "id", "email", "firstName", "middleName", "lastName", "status"
            switch (cellIndex) {
                case 0:
//                    artist.setId((long) currentCell.getNumericCellValue());
                    break;
                case 1:
                    artist.setEmail(currentCell.getStringCellValue());
                    break;

                case 2:
                    artist.setFirstName(currentCell.getStringCellValue());
                    break;

                case 3:
                    artist.setMiddleName(currentCell.getStringCellValue());
                    break;

                case 4:
                    artist.setLastName(currentCell.getStringCellValue());
                    break;

                default:
                    break;
            }

            cellIndex++;
        }

        return artist;
    }

    /**
     * @param csvRecord
     * @return
     */
    @Override
    public Artist readCSVRecord(CSVRecord csvRecord) {
        LOGGER.debug("+readCSVRecord({})", csvRecord);
        Artist artist = new Artist();
        // "id", "email", "firstName", "middleName", "lastName", "status"
        artist.setId(Parser.asType(csvRecord.get(1), Long.class)); // id
        artist.setEmail(csvRecord.get(2)); // email
        artist.setFirstName(csvRecord.get(3)); // firstName
        artist.setMiddleName(csvRecord.get(4)); // middleName
        artist.setLastName(csvRecord.get(5)); // lastName
        LOGGER.debug("-readCSVRecord(), artist:{}", artist);
        return artist;
    }

    /**
     * @param artist
     * @return
     */
    @Override
    public List<String> buildRowCells(Artist artist) {
        List<String> artistContents = new LinkedList<>();
        // "id", "email", "firstName", "middleName", "lastName", "status"
        artistContents.add(BeanUtils.toString(artist.getId()));
        artistContents.add(artist.getEmail());
        artistContents.add(artist.getFirstName());
        artistContents.add(artist.getMiddleName());
        artistContents.add(artist.getLastName());
        return artistContents;
    }

}
