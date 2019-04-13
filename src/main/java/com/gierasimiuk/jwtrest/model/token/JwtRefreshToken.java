package com.gierasimiuk.jwtrest.model.token;

import com.gierasimiuk.jwtrest.config.JwtSettings;

/**
 * Definition of a JWT refresh token. 
 * 
 * @author Michael Gierasimiuk
 */
public class JwtRefreshToken extends JwtToken {

    JwtRefreshToken(String token) {
        super(token);
    }

    public long getExpiry() {
        return JwtSettings.REFRESH_TOKEN_EXPIRY;
    }
}