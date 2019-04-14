package com.gierasimiuk.jwtrest.model;

/**
 * Encapsulates a user identifier and access token.
 * 
 * @author Michael Gierasimiuk
 */
public class UserToken {

    private String id;
    private String token;

    public UserToken() { 
    }

    public UserToken(String id, String token) {
        this.id = id;
        this.token = token;
    }

    public String getId() {
		return this.id;
    }

	public String getToken() {
		return this.token;
	}

    public String toString() {
        return "ID: " + this.id 
            + ", Token: " + this.token;
    }
}