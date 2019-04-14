package com.gierasimiuk.jwtrest.model;

/**
 * Encapsulates a user identifier and access token.
 * 
 * @author Michael Gierasimiuk
 */
public class UserAccessToken {

    private String id;
    private String access_token;

    public UserAccessToken(String id, String access_token) {
        this.id = id;
        this.access_token = access_token;
    }

    public String getId() {
		return this.id;
    }

	public String getAccess_token() {
		return this.access_token;
	}

    public String toString() {
        return "ID: " + this.id 
            + ", Refresh Token: " + this.access_token;
    }
}