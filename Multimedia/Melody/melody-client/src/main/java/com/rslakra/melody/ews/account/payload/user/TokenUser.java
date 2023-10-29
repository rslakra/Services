package com.rslakra.melody.ews.account.payload.user;

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
public class TokenUser {

    private String id;
    private String userName;
    private Long expiry;
    private String sub;
    private String iss;
    private String realm;

    private String clientId;
    private String clientSecret;
    private String tokenType;
    private List<String> scopes;
    private List<String> description;
    private List<String> displayName;
    private Boolean active;

}
