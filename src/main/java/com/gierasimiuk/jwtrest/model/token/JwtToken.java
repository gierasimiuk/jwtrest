package com.gierasimiuk.jwtrest.model.token;

/**
 * Abstraction definition of a generic JSON Web Token (JWT). 
 * 
 * @author Michael Gierasimiuk
 */
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