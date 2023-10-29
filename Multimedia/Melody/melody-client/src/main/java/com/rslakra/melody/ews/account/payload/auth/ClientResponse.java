package com.rslakra.melody.ews.account.payload.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 12/5/22 22:53 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class ClientResponse {

    private String userName;
    private String description;
    private String displayName;
    private String realm;
    private String tokenType;
    private List<String> scopes;
    private List<String> roles;
    private Long resourceId;
    private String clientId;
    private String clientSecret;
    private Boolean active;
}
