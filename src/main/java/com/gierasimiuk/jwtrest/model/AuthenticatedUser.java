package com.gierasimiuk.jwtrest.model;

import com.gierasimiuk.jwtrest.model.token.JwtAccessToken;
import com.gierasimiuk.jwtrest.model.token.JwtRefreshToken;

public class AuthenticatedUser {

    private String id;
    private String username;
    private String password;
    private JwtAccessToken access_token;
    private JwtRefreshToken refresh_token;

    public AuthenticatedUser(User user, JwtAccessToken accessToken, JwtRefreshToken refreshToken) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.access_token = accessToken;
        this.refresh_token = refreshToken;
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
    
    public JwtAccessToken getAccessToken() {
		return this.access_token;
    }
    
    public JwtRefreshToken getRefreshToken() {
		return this.refresh_token;
	}
}