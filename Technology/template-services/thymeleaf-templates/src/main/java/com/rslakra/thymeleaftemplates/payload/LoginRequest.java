package com.rslakra.thymeleaftemplates.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rohtash Lakra
 * @created 7/14/23 7:42 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {

    private String email;
    private String password;
}
