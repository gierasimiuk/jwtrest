package com.gierasimiuk.jwtrest.model.token;

import com.gierasimiuk.jwtrest.config.JwtSettings;

public class JwtAccessToken extends JwtToken {

    JwtAccessToken(String token) {
        super(token);
    }

    public long getExpiry() {
        return JwtSettings.ACCESS_TOKEN_EXPIRY;
    }
}