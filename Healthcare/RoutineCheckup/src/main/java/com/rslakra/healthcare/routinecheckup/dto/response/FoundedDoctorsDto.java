package com.rslakra.healthcare.routinecheckup.dto.response;


import lombok.Data;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:09 PM
 */
@Data
public class FoundedDoctorsDto {

    private String searchString;

    private List<DoctorResponseDto> doctors;

}
