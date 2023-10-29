package com.rslakra.services.automobile.dto;

import lombok.Data;

/**
 * @author Rohtash Lakra
 * @created 4/19/23 3:53 PM
 */
@Data
public class LoginRequest {

    private String username;
    private String password;
    private String _csrf;

}
