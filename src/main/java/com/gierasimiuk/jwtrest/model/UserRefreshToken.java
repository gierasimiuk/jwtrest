package com.gierasimiuk.jwtrest.model;

/**
 * Encapsulates a user identifier and refresh token.
 * 
 * @author Michael Gierasimiuk
 */
public class UserRefreshToken {

    private String id;
    private String refresh_token;

    public UserRefreshToken(String id, String refresh_token) {
        this.id = id;
        this.refresh_token = refresh_token;
    }

    public String getId() {
		return this.id;
    }

	public String getRefresh_token() {
		return this.refresh_token;
	}

    public String toString() {
        return "ID: " + this.id 
            + ", Refresh Token: " + this.refresh_token;
    }
}