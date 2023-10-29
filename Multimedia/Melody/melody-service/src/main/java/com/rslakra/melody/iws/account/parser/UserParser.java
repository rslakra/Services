package com.rslakra.melody.iws.account.parser;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.core.enums.EntityStatus;
import com.devamatre.framework.spring.parser.Parser;
import com.devamatre.framework.spring.parser.csv.CsvParser;
import com.devamatre.framework.spring.parser.excel.ExcelParser;
import com.rslakra.melody.iws.account.persistence.entity.User;
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
public class UserParser implements ExcelParser<User>, CsvParser<User>, Parser<User> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserParser.class);

    public static final String CSV_DOWNLOAD_FILE_NAME = "users.csv";
    public static final String EXCEL_DOWNLOAD_FILE_NAME = "users.xlsx";
    public static final String[] HEADERS = {
        "id", "email", "firstName", "middleName", "lastName", "status"
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
        return User.class.getSimpleName();
    }

    /**
     * @param rowCells
     * @return
     */
    @Override
    public User readCells(Iterator<Cell> rowCells) {
        User user = new User();
        int cellIndex = 0;
        while (rowCells.hasNext()) {
            Cell currentCell = rowCells.next();
            // "id", "email", "firstName", "middleName", "lastName", "status"
            switch (cellIndex) {
                case 0:
//                    user.setId((long) currentCell.getNumericCellValue());
                    break;
                case 1:
                    user.setEmail(currentCell.getStringCellValue());
                    break;

                case 2:
                    user.setFirstName(currentCell.getStringCellValue());
                    break;

                case 3:
                    user.setMiddleName(currentCell.getStringCellValue());
                    break;

                case 4:
                    user.setLastName(currentCell.getStringCellValue());
                    break;

                case 5:
                    user.setStatus(EntityStatus.ofString(currentCell.getStringCellValue()));
                    break;

                default:
                    break;
            }

            cellIndex++;
        }

        return user;
    }

    /**
     * @param csvRecord
     * @return
     */
    @Override
    public User readCSVRecord(CSVRecord csvRecord) {
        LOGGER.debug("+readCSVRecord({})", csvRecord);
        User user = new User();
        // "id", "email", "firstName", "middleName", "lastName", "status"
        user.setId(Parser.asType(csvRecord.get(1), Long.class)); // id
        user.setEmail(csvRecord.get(2)); // email
        user.setFirstName(csvRecord.get(3)); // firstName
        user.setMiddleName(csvRecord.get(4)); // middleName
        user.setLastName(csvRecord.get(5)); // lastName
        user.setStatus(EntityStatus.ofString(csvRecord.get(6))); // status
        LOGGER.debug("-readCSVRecord(), user:{}", user);
        return user;
    }

    /**
     * @param user
     * @return
     */
    @Override
    public List<String> buildRowCells(User user) {
        List<String> userContents = new LinkedList<>();
        // "id", "email", "firstName", "middleName", "lastName", "status"
        userContents.add(BeanUtils.toString(user.getId()));
        userContents.add(user.getEmail());
        userContents.add(user.getFirstName());
        userContents.add(user.getMiddleName());
        userContents.add(user.getLastName());
        userContents.add(user.getStatus().name());
        return userContents;
    }

}
