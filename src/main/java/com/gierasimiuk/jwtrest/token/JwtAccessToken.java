package com.gierasimiuk.jwtrest.token;

import com.gierasimiuk.jwtrest.config.JwtSettings;

/**
 * {@link JwtAccessToken} class.
 * 
 * @author Michael Gierasimiuk
 */
public class JwtAccessToken extends JwtToken {

    public JwtAccessToken(String token) {
        super(token);
    }

    public long getTTL() {
        return JwtSettings.ACCESS_TOKEN_EXPIRY;
    }
}