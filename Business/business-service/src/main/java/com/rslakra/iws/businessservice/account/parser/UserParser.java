package com.rslakra.iws.businessservice.account.parser;

import com.devamatre.framework.spring.parser.AbstractParser;
import com.devamatre.framework.spring.parser.csv.CsvParser;
import com.devamatre.framework.spring.parser.excel.ExcelParser;
import com.rslakra.iws.businessservice.account.persistence.entity.User;
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
public class UserParser extends AbstractParser<User> implements ExcelParser<User>, CsvParser<User> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserParser.class);
    public static final String CSV_DOWNLOAD_FILE_NAME = "users.csv";
    public static final String EXCEL_DOWNLOAD_FILE_NAME = "users.xlsx";

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
     * Returns the name of the Excel sheet.
     *
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

                default:
                    break;
            }

            cellIndex++;
        }

        //set username as password
        user.setPassword(user.getEmail());

        return user;
    }

    /**
     * @param csvRecord
     * @return
     */
    @Override
    public User readCSVRecord(CSVRecord csvRecord) {
        LOGGER.debug("+buildCSVRecord({})", csvRecord);
        User user = new User();
        user.setEmail(csvRecord.get(1)); // email
        user.setFirstName(csvRecord.get(2)); // firstName
        user.setMiddleName(csvRecord.get(3)); // middleName
        user.setLastName(csvRecord.get(4)); // lastName
        user.setPassword(user.getEmail());
        LOGGER.debug("-buildCSVRecord(), user:{}", user);
        return user;
    }

    /**
     * @param user
     * @return
     */
    @Override
    public List<String> buildRowCells(User user) {
        List<String> userContents = new LinkedList<>();
        userContents.add(user.getId().toString());
        userContents.add(user.getEmail());
        userContents.add(user.getFirstName());
        userContents.add(user.getMiddleName());
        userContents.add(user.getLastName());
        return userContents;
    }

}
