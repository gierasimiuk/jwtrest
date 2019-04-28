package com.gierasimiuk.jwtrest.model;

/**
 * {@link UserToken} class.
 * 
 * @author Michael Gierasimiuk
 */
public abstract class UserToken {

    protected String id;
    protected String token;

    public UserToken(String id, String token) {
        this.id = id;
        this.token = token;
    }

    public String getUser_id() {
		return this.id;
    }
}