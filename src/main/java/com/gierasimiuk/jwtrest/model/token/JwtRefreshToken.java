package com.gierasimiuk.jwtrest.model.token;

import com.gierasimiuk.jwtrest.config.JwtSettings;

public class JwtRefreshToken extends JwtToken {

    JwtRefreshToken(String token) {
        super(token);
    }

    public long getExpiry() {
        return JwtSettings.REFRESH_TOKEN_EXPIRY;
    }
}