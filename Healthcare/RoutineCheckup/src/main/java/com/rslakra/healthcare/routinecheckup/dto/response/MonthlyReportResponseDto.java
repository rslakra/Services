package com.rslakra.healthcare.routinecheckup.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:09 PM
 */
@Data
public class MonthlyReportResponseDto {

    @JsonProperty("file_name")
    private String fileName;

}
