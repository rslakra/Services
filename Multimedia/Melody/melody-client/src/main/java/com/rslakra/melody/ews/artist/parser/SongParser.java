package com.rslakra.melody.ews.artist.parser;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.spring.parser.Parser;
import com.devamatre.framework.spring.parser.csv.CsvParser;
import com.devamatre.framework.spring.parser.excel.ExcelParser;
import com.rslakra.melody.ews.artist.payload.Song;
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
public class SongParser implements ExcelParser<Song>, CsvParser<Song>, Parser<Song> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SongParser.class);

    public static final String CSV_DOWNLOAD_FILE_NAME = "songs.csv";
    public static final String EXCEL_DOWNLOAD_FILE_NAME = "songs.xlsx";
    public static final String[] HEADERS = {
        "id", "artistId", "title", "rating"
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
        return Song.class.getSimpleName();
    }

    /**
     * @param rowCells
     * @return
     */
    @Override
    public Song readCells(Iterator<Cell> rowCells) {
        Song song = new Song();
        int cellIndex = 0;
        while (rowCells.hasNext()) {
            Cell currentCell = rowCells.next();
            // "id", "artistId", "title", "rating"
            switch (cellIndex) {
                case 0:
//                    song.setId((long) currentCell.getNumericCellValue());
                    break;
                case 1:
                    song.setArtistId(Parser.asType(currentCell.getStringCellValue(), Long.class));
                    break;

                case 2:
                    song.setTitle(currentCell.getStringCellValue());
                    break;

                case 3:
                    song.setRating(Parser.asType(currentCell.getStringCellValue(), Integer.class));
                    break;

                default:
                    break;
            }

            cellIndex++;
        }

        return song;
    }

    /**
     * @param csvRecord
     * @return
     */
    @Override
    public Song readCSVRecord(CSVRecord csvRecord) {
        LOGGER.debug("+readCSVRecord({})", csvRecord);
        Song song = new Song();
        // "id", "artistId", "title", "rating"
        song.setId(Parser.asType(csvRecord.get(1), Long.class)); // id
        song.setArtistId(Parser.asType(csvRecord.get(2), Long.class)); // artistId
        song.setTitle(csvRecord.get(3)); // title
        song.setRating(Parser.asType(csvRecord.get(4), Integer.class)); // rating
        LOGGER.debug("-readCSVRecord(), song:{}", song);
        return song;
    }

    /**
     * @param song
     * @return
     */
    @Override
    public List<String> buildRowCells(Song song) {
        List<String> songContents = new LinkedList<>();
        // "id", "artistId", "title", "rating"
        songContents.add(BeanUtils.toString(song.getId()));
        songContents.add(BeanUtils.toString(song.getArtistId()));
        songContents.add(song.getTitle());
        songContents.add(BeanUtils.toString(song.getRating()));
        return songContents;
    }

}
