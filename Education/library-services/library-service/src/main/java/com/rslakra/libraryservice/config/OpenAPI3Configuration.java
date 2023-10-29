package com.rslakra.libraryservice.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

/**
 * @author Rohtash Lakra
 * @created 8/27/21 5:02 PM
 */
@SecurityScheme(name = "basicAuth", type = SecuritySchemeType.HTTP, scheme = "basic")
public class OpenAPI3Configuration {

}
