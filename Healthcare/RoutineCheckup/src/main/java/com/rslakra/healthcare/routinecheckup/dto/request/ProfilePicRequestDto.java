package com.rslakra.healthcare.routinecheckup.dto.request;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:06 PM
 */
@Data
public class ProfilePicRequestDto {

    @NotEmpty
    private String url;

}
