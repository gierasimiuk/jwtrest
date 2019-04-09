package com.gierasimiuk.jwtrest.model.token;

public abstract class JwtToken {

    private final String token;

    public JwtToken(String token) {
        this.token = token;
    }

	public String getToken() {
		return token;
    }
    
    abstract public long getExpiry();
}