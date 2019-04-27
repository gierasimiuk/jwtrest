package com.gierasimiuk.jwtrest.model;

/**
 * {@link AuthenticatedUser} class.
 * 
 * @author Michael Gierasimiuk
 */
public class AuthenticatedUser {

    private String id;
    private String username;
    private String password;
    private String access_token;
    private String refresh_token;

    public AuthenticatedUser(User user, String access_token, String refresh_token) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.access_token = access_token;
        this.refresh_token = refresh_token;
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
    
    public String getAccess_token() {
		return this.access_token;
    }
    
    public String getRefresh_token() {
		return this.refresh_token;
    }
    
    public void updateAccessToken(String accessToken) {
        this.access_token = accessToken;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refresh_token = refreshToken;
    }
}