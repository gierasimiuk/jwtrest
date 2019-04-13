package com.gierasimiuk.jwtrest.model.token;

import com.gierasimiuk.jwtrest.config.JwtSettings;

/**
 * Definition of a JWT access token. 
 * 
 * @author Michael Gierasimiuk
 */
public class JwtAccessToken extends JwtToken {

    JwtAccessToken(String token) {
        super(token);
    }

    public long getExpiry() {
        return JwtSettings.ACCESS_TOKEN_EXPIRY;
    }
}