package com.gierasimiuk.jwtrest.model;

public class AuthenticatedUser {

    private String id;
    private String username;
    private String password;
    private String atoken;
    private String rtoken;

    public AuthenticatedUser(
            String id, String username, String password, String atoken, String rtoken) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.atoken = atoken;
        this.rtoken = rtoken;
    }

    public String getAccessToken() {
		return this.atoken;
    }

    public String getRefreshToken() {
		return this.rtoken;
    }

    public String getId() {
		return this.id;
    }

	public String getUsername() {
		return this.username;
	}
    
    public String getPassword() {
		return this.password;
	}
}