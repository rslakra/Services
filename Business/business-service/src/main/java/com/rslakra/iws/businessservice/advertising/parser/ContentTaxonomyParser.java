package com.rslakra.iws.businessservice.advertising.parser;

import com.rslakra.frameworks.core.BeanUtils;
import com.rslakra.frameworks.spring.parser.AbstractParser;
import com.rslakra.frameworks.spring.parser.Parser;
import com.rslakra.frameworks.spring.parser.csv.CsvParser;
import com.rslakra.frameworks.spring.parser.excel.ExcelParser;
import com.rslakra.iws.businessservice.advertising.persistence.entity.ContentTaxonomy;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 4/5/23 4:55 PM
 */
public class ContentTaxonomyParser extends AbstractParser<ContentTaxonomy>
    implements ExcelParser<ContentTaxonomy>, CsvParser<ContentTaxonomy> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContentTaxonomyParser.class);
    public static final String CSV_DOWNLOAD_FILE_NAME = "contentTaxonomy.csv";
    public static final String EXCEL_DOWNLOAD_FILE_NAME = "contentTaxonomy.xlsx";

    public static final String[] HEADERS = {
        "id", "parentId", "name", "tier1", "tier2", "tier13", "tier4", "extension", "score", "confident"
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
     * Returns the name of the Excel sheet.
     *
     * @return
     */
    @Override
    public String getSheetName() {
        return ContentTaxonomy.class.getSimpleName();
    }

    /**
     * @param rowCells
     * @return
     */
    @Override
    public ContentTaxonomy readCells(Iterator<Cell> rowCells) {
        // "id", "parentId", "name", "tier1", "tier2", "tier13", "tier4", "extension", "score", "confident"
        ContentTaxonomy contentTaxonomy = new ContentTaxonomy();
        int cellIndex = 0;
        while (rowCells.hasNext()) {
            Cell currentCell = rowCells.next();
            switch (cellIndex) {
                case 0:
//                    contentTaxonomy.setId((long) currentCell.getNumericCellValue());
                    break;
                case 1:
                    contentTaxonomy.setParentId(Parser.asType(currentCell.getStringCellValue(), Long.class));
                    break;

                case 2:
                    contentTaxonomy.setName(currentCell.getStringCellValue());
                    break;

                case 3:
                    contentTaxonomy.setTier1(currentCell.getStringCellValue());
                    break;

                case 4:
                    contentTaxonomy.setTier2(currentCell.getStringCellValue());
                    break;

                case 5:
                    contentTaxonomy.setTier3(currentCell.getStringCellValue());
                    break;

                case 6:
                    contentTaxonomy.setTier4(currentCell.getStringCellValue());
                    break;

                case 7:
                    contentTaxonomy.setExtension(currentCell.getStringCellValue());
                    break;

                case 8:
                    contentTaxonomy.setScore(Parser.asType(currentCell.getStringCellValue(), BigDecimal.class));
                    break;

                case 9:
                    contentTaxonomy.setConfident(Parser.asType(currentCell.getStringCellValue(), Boolean.class));
                    break;

                default:
                    break;
            }

            cellIndex++;
        }

        return contentTaxonomy;
    }

    /**
     * @param csvRecord
     * @return
     */
    @Override
    public ContentTaxonomy readCSVRecord(CSVRecord csvRecord) {
        LOGGER.debug("+buildCSVRecord({})", csvRecord);
        // "id", "parentId", "name", "tier1", "tier2", "tier13", "tier4", "extension", "score", "confident"
        ContentTaxonomy contentTaxonomy = new ContentTaxonomy();
        contentTaxonomy.setId(Parser.asType(csvRecord.get(1), Long.class)); // id
        contentTaxonomy.setParentId(Parser.asType(csvRecord.get(2), Long.class)); // parentId
        contentTaxonomy.setName(csvRecord.get(3)); // name
        contentTaxonomy.setTier1(csvRecord.get(4)); // tier1
        contentTaxonomy.setTier2(csvRecord.get(5)); // tier2
        contentTaxonomy.setTier3(csvRecord.get(6)); // tier3
        contentTaxonomy.setTier4(csvRecord.get(7)); // tier4
        contentTaxonomy.setExtension(csvRecord.get(8)); // extension
        contentTaxonomy.setScore(Parser.asType(csvRecord.get(9), BigDecimal.class)); // score
        contentTaxonomy.setConfident(Parser.asType(csvRecord.get(10), Boolean.class)); // confident

        LOGGER.debug("-buildCSVRecord(), contentTaxonomy:{}", contentTaxonomy);
        return contentTaxonomy;
    }

    /**
     * @param contentTaxonomy
     * @return
     */
    @Override
    public List<String> buildRowCells(ContentTaxonomy contentTaxonomy) {
        List<String> contentTaxonomies = new LinkedList<>();
        // "id", "parentId", "name", "tier1", "tier2", "tier13", "tier4", "extension", "score", "confident"
        contentTaxonomies.add(contentTaxonomy.getId().toString());
        contentTaxonomies.add(BeanUtils.toString(contentTaxonomy.getParentId()));
        contentTaxonomies.add(contentTaxonomy.getName());
        contentTaxonomies.add(contentTaxonomy.getTier1());
        contentTaxonomies.add(contentTaxonomy.getTier2());
        contentTaxonomies.add(contentTaxonomy.getTier3());
        contentTaxonomies.add(contentTaxonomy.getTier4());
        contentTaxonomies.add(contentTaxonomy.getExtension());
        contentTaxonomies.add(BeanUtils.toString(contentTaxonomy.getScore()));
        contentTaxonomies.add(BeanUtils.toString(contentTaxonomy.getConfident()));

        return contentTaxonomies;
    }

}
