package com.gierasimiuk.jwtrest.service;

import com.gierasimiuk.jwtrest.model.AuthenticatedUser;
import com.gierasimiuk.jwtrest.model.User;
import com.gierasimiuk.jwtrest.model.token.JwtAccessToken;
import com.gierasimiuk.jwtrest.model.token.JwtRefreshToken;
import com.gierasimiuk.jwtrest.model.token.JwtTokenFactory;

public class AuthService {
    
    private JwtTokenFactory tokenFactory;

    public AuthService() {
        this.tokenFactory = new JwtTokenFactory();
    }

    public AuthenticatedUser login(User user, String username, String password) {

        if (user.getUsername().equals(username) == false) {
            throw new IllegalArgumentException("Username was not correct");
        }
        
        if (user.getPassword().equals(password) == false) {
            throw new IllegalArgumentException("Password was not correct");
        }

        JwtAccessToken accessToken = tokenFactory.createJwtAccessToken(user);
        JwtRefreshToken refreshToken = tokenFactory.createJwtRefreshToken(user);

        return new AuthenticatedUser(user, accessToken, refreshToken);
    }

    public String token() {
        return "";
    }
}
