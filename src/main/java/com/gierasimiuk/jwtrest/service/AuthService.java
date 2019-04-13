package com.gierasimiuk.jwtrest.service;

import java.util.HashMap;
import java.util.Map;

import com.gierasimiuk.jwtrest.model.AuthenticatedUser;
import com.gierasimiuk.jwtrest.model.User;
import com.gierasimiuk.jwtrest.model.token.JwtAccessToken;
import com.gierasimiuk.jwtrest.model.token.JwtRefreshToken;
import com.gierasimiuk.jwtrest.model.token.JwtTokenFactory;

/**
 * Service for handling authentication. 
 * 
 * @author Michael Gierasimiuk
 */
public class AuthService {
    
    private final JwtTokenFactory tokenFactory;
    private final Map<String, AuthenticatedUser> authUsers;

    /**
     * Creates a new {@link AuthService}. 
     */
    public AuthService() {
        this.tokenFactory = new JwtTokenFactory();
        this.authUsers = new HashMap<String, AuthenticatedUser>();
    }

    /**
     * Processes a login request given an existing user and provided username 
     * and password from the client. 
     * 
     * @param user an existing user in the system. Must not be null.
     * @param username the username provided by the client.
     * @param password the password provided by the client. 
     * @return the {@link AuthenticatedUser} bundled together with an access JWT and a refresh JWT. 
     */
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

    /**
     * TODO: Refreshes a token.
     * 
     * @param username the username.
     * @param token the refresh token. 
     * @return the new refresh token with an updated TTL.
     */
    public String token(String username, String token) {
    	// TODO: Implement
    	return token;
    }

    /**
     * Finds the {@link AuthenticatedUser} with the given unique identifier and returns
     * the result. 
     * 
     * @param id the unique identifier of the {@link AuthenticatedUser}.
     * @return the {@link AuthenticatedUser} with the given identifier.
     */
    public AuthenticatedUser getAuthenticatedUser(String id) {
        return authUsers.get(id);
    }
}
