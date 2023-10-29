package com.rslakra.healthcare.routinecheckup.dto.request;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:06 PM
 */
@Data
public class UpdateMonthlyReportRequestDto {

    @NotEmpty
    private String oldName;

    @NotEmpty
    private String newName;

}
