package com.gierasimiuk.jwtrest.model;

/**
 * {@link UserAccessToken} class.
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

    public String getUser_id() {
		return this.id;
    }

	public String getAccess_token() {
		return this.access_token;
	}

    public String toString() {
        return "user_id: " + this.id 
            + ", Refresh Token: " + this.access_token;
    }
}