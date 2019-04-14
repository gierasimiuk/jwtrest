package com.gierasimiuk.jwtrest.token;

import java.util.Date;

import com.gierasimiuk.jwtrest.config.JwtSettings;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

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
    
    abstract public long getTTL();

    public Date getExpiration() {
        return this.parse().getExpiration();
    }

    // Should be the same as username of the user 
    public String getSubject() {
        return this.parse().getSubject();
    }

    // Should be the same as the unique id of the user
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
        } catch (Exception e) {
            return null;
        }
    }

    public static Claims parse(String token) {
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
