package com.rslakra.userservice.payload.auth;

import com.rslakra.userservice.payload.Scope;
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
public class ClientRequest {

    private String userName;
    private String realm;
    private String description;
    private String tokenType;
    private List<Scope> scopes;
    private List<String> roles;
    private Long resourceId;

}
