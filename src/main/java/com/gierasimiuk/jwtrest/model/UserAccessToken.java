package com.gierasimiuk.jwtrest.model;

/**
 * Encapsulates a user user_identifier and access token.
 * 
 * @author Michael Gierasimiuk
 */
public class UserAccessToken {

    private String user_id;
    private String access_token;

    public UserAccessToken(String user_id, String access_token) {
        this.user_id = user_id;
        this.access_token = access_token;
    }

    public String getUser_id() {
		return this.user_id;
    }

	public String getAccess_token() {
		return this.access_token;
	}

    public String toString() {
        return "user_id: " + this.user_id 
            + ", Refresh Token: " + this.access_token;
    }
}