package com.gierasimiuk.jwtrest.service;

import java.util.HashMap;
import java.util.Map;

import com.gierasimiuk.jwtrest.model.AuthenticatedUser;
import com.gierasimiuk.jwtrest.model.User;
import com.gierasimiuk.jwtrest.model.token.JwtAccessToken;
import com.gierasimiuk.jwtrest.model.token.JwtRefreshToken;
import com.gierasimiuk.jwtrest.model.token.JwtTokenFactory;

public class AuthService {
    
    private final JwtTokenFactory tokenFactory;
    private final Map<String, AuthenticatedUser> authUsers;

    public AuthService() {
        this.tokenFactory = new JwtTokenFactory();
        this.authUsers = new HashMap<String, AuthenticatedUser>();
    }

    public AuthenticatedUser login(User user, String username, String password) {

        if (user.getUsername().equals(username) == false) {
            throw new IllegalArgumentException("Username was not correct");
        }
        
        if (user.getPassword().equals(password) == false) {
            throw new IllegalArgumentException("Password was not correct");
        }

        if (authUsers.containsKey(user.getId())) {
            AuthenticatedUser authUser = authUsers.get(user.getId());
            // TODO: Should we update the token's TTL here or only upon token refresh? 
            return authUser;
        } else {
            JwtAccessToken accessToken = tokenFactory.createJwtAccessToken(user);
            JwtRefreshToken refreshToken = tokenFactory.createJwtRefreshToken(user);
            
            AuthenticatedUser authUser = new AuthenticatedUser(user, accessToken, refreshToken);
            authUsers.put(authUser.getId(), authUser);
            
            return authUser;
        }
    }

    public String token() {
        return "";
    }

    public AuthenticatedUser getAuthenticatedUser(String id) {
        return authUsers.get(id);
    }
}
