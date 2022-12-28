package com.rslakra.jwtauthentication2.payload;

import java.io.Serializable;

public class Request implements Serializable {

    private static final long serialVersionUID = 7566773931289771902L;
    private String userName;
    private String password;

    /**
     * need default constructor for JSON Parsing
     */
    public Request() {
    }

    public Request(final String userName, final String password) {
        this.setUserName(userName);
        this.setPassword(password);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
