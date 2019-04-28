package com.gierasimiuk.jwtrest.model;

/**
 * {@link UserAccessToken} class.
 * 
 * @author Michael Gierasimiuk
 */
public class UserAccessToken extends UserToken {

    public UserAccessToken(String id, String access_token) {
        super(id, access_token);
    }

	public String getAccess_token() {
		return this.token;
	}

    public String toString() {
        return "User ID: " + this.id 
            + ", Refresh Token: " + this.token;
    }
}