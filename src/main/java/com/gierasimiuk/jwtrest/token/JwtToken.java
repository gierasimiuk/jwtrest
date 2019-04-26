package com.gierasimiuk.jwtrest.token;

import java.util.Date;

import com.gierasimiuk.jwtrest.config.JwtSettings;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

/**
 * {@link JwtToken} base class for a JSON Web Token
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
    
    abstract public long getTTL();
    
    public Date getExpiration() {
        return this.parse().getExpiration();
    }

    // Used to store the username
    public String getSubject() {
        return this.parse().getSubject();
    }

    // Used to store the user id
    public String getId() {
        return this.parse().getId();
    }

    public Claims parse() {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(JwtSettings.SIGNING_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            return body;
        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }
}
