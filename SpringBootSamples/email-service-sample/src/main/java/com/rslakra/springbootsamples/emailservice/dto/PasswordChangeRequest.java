package com.rslakra.springbootsamples.emailservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rohtash Lakra
 * @created 1/6/22 4:33 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class PasswordChangeRequest {

    private String newPassword;
    private String confirmPassword;
    private String currentPassword;

}
