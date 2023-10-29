package com.rslakra.healthcare.routinecheckup.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:06 PM
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDto {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

}
