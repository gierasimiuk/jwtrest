package com.gierasimiuk.jwtrest.model;

/**
 * {@link UserRefreshToken} class.
 * 
 * @author Michael Gierasimiuk
 */
public class UserRefreshToken extends UserToken {

    public UserRefreshToken(String id, String token) {
        super(id, token);
    }

	public String getRefresh_token() {
		return this.token;
	}

    public String toString() {
        return "User ID: " + this.id 
            + ", Refresh Token: " + this.token;
    }
}