package com.rslakra.jwtauthentication5.payload.response;

import java.util.List;

public class JwtResponse {

    private final String tokenType = "Bearer";
    private final String accessToken;
    private final Long id;
    private final String userName;
    private final String email;
    private final List<String> roles;

    /**
     * @param accessToken
     * @param id
     * @param userName
     * @param email
     * @param roles
     */
    public JwtResponse(String accessToken, Long id, String userName, String email, List<String> roles) {
        this.accessToken = accessToken;
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.roles = roles;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getRoles() {
        return roles;
    }
}
