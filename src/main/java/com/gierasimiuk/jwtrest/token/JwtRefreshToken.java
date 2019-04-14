package com.gierasimiuk.jwtrest.token;

import com.gierasimiuk.jwtrest.config.JwtSettings;

/**
 * Definition of a JWT refresh token. 
 * 
 * @author Michael Gierasimiuk
 */
public class JwtRefreshToken extends JwtToken {

    public JwtRefreshToken(String token) {
        super(token);
    }

    public long getTTL() {
        return JwtSettings.REFRESH_TOKEN_EXPIRY;
    }
}