package com.gierasimiuk.jwtrest.model;

/**
 * Encapsulates a user user_identifier and refresh token.
 * 
 * @author Michael Gierasimiuk
 */
public class UserRefreshToken {

    private String user_id;
    private String refresh_token;

    public UserRefreshToken(String user_id, String refresh_token) {
        this.user_id = user_id;
        this.refresh_token = refresh_token;
    }

    public String getUser_id() {
		return this.user_id;
    }

	public String getRefresh_token() {
		return this.refresh_token;
	}

    public String toString() {
        return "User ID: " + this.user_id 
            + ", Refresh Token: " + this.refresh_token;
    }
}