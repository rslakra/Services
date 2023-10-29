package com.rslakra.iws.businessservice.account.parser;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.core.enums.EntityStatus;
import com.devamatre.framework.spring.parser.AbstractParser;
import com.devamatre.framework.spring.parser.csv.CsvParser;
import com.devamatre.framework.spring.parser.excel.ExcelParser;
import com.rslakra.iws.businessservice.account.persistence.entity.Role;
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
public class RoleParser extends AbstractParser<Role> implements ExcelParser<Role>, CsvParser<Role> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleParser.class);
    public static final String CSV_DOWNLOAD_FILE_NAME = "roles.csv";
    public static final String EXCEL_DOWNLOAD_FILE_NAME = "roles.xlsx";
    public static final String[] HEADERS = {
        "id", "name", "status"
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
        return Role.class.getSimpleName();
    }

    /**
     * @param rowCells
     * @return
     */
    @Override
    public Role readCells(Iterator<Cell> rowCells) {
        Role role = new Role();
        int cellIndex = 0;
        while (rowCells.hasNext()) {
            Cell currentCell = rowCells.next();
            switch (cellIndex) {
                case 0:
//                    role.setId((long) currentCell.getNumericCellValue());
                    break;
                case 1:
                    role.setName(currentCell.getStringCellValue());
                    break;

                case 2:
                    role.setStatus(EntityStatus.ofString(currentCell.getStringCellValue()));
                    break;

                default:
                    break;
            }

            cellIndex++;
        }

        return role;
    }

    /**
     * @param csvRecord
     * @return
     */
    @Override
    public Role readCSVRecord(CSVRecord csvRecord) {
        LOGGER.debug("+buildCSVRecord({})", csvRecord);
        Role role = new Role();
        role.setName(csvRecord.get(1)); // name
        role.setStatus(EntityStatus.ofString(csvRecord.get(2))); // status
        LOGGER.debug("-buildCSVRecord(), role:{}", role);
        return role;
    }

    /**
     * @param role
     * @return
     */
    @Override
    public List<String> buildRowCells(Role role) {
        List<String> roleContents = new LinkedList<>();
        roleContents.add(BeanUtils.toString(role.getId()));
        roleContents.add(role.getName());
        roleContents.add(role.getStatus().name());
        return roleContents;
    }

}
