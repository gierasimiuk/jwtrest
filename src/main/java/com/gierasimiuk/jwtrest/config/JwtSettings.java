package com.gierasimiuk.jwtrest.config;

import com.gierasimiuk.jwtrest.token.JwtAccessToken;
import com.gierasimiuk.jwtrest.token.JwtRefreshToken;

public class JwtSettings {

    /**
     * {@link JwtAccessToken} expire time in milliseconds.
     */
    public static final long ACCESS_TOKEN_EXPIRY = 24 * 60 * 60 * 1000;

    /**
     * {@link JwtRefreshToken} expire time in milliseconds.
     */
    public static final long REFRESH_TOKEN_EXPIRY = 24 * 60 * 60 * 1000 * 7;
    
    /**
     * Token signing key.
     */
    public static final String SIGNING_KEY = "55FfXMiINEdt1XR85VipRLSOkT6kSpzs26shwy63hdx1m";

    /**
     * Token issuer string. 
     */
    public static final String ISSUER = "http://gierasimiuk.com";
}