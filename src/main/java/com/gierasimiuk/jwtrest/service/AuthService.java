package com.gierasimiuk.jwtrest.service;

import java.util.HashMap;
import java.util.Map;

import com.gierasimiuk.jwtrest.model.AuthenticatedUser;
import com.gierasimiuk.jwtrest.model.User;
import com.gierasimiuk.jwtrest.token.JwtAccessToken;
import com.gierasimiuk.jwtrest.token.JwtRefreshToken;
import com.gierasimiuk.jwtrest.token.JwtTokenFactory;

/**
 * {@link AuthService} service to handle JWT authentication.
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
     * Processes a login attempt given an existing user found in the system and
     * user credentials sent from a client.
     * 
     * @param existing the existing user in the system. Must not be null.
     * @param user the user credentials sent from the client. Must not be null.
     * @return the {@link AuthenticatedUser} containing JWTs.
     */
    public AuthenticatedUser login(User existing, User user) throws IllegalArgumentException {
        if (existing.getUsername().equals(user.getUsername()) == false) {
            throw new IllegalArgumentException("Incorrect username");
        }
        if (existing.getPassword().equals(user.getPassword()) == false) {
            throw new IllegalArgumentException("Incorrect password");
        }
        if (existing.getId().equals(user.getId()) == false) {
            throw new IllegalArgumentException("User IDs do not match");
        }
        if (authUsers.containsKey(user.getId())) {
            return authUsers.get(user.getId());
        } else {
            JwtAccessToken accessToken = 
                tokenFactory.createJwtAccessToken(user);
            JwtRefreshToken refreshToken = 
                tokenFactory.createJwtRefreshToken(user);
            
            AuthenticatedUser authUser = new AuthenticatedUser(
                user, accessToken.getToken(), refreshToken.getToken());
            authUsers.put(authUser.getId(), authUser);
            
            return authUser;
        }
    }

    /**
     * Creates a new access token given a valid refresh token. If the given 
     * refresh token is expired, an exception is thrown. This method will also
     * update the access token in its stored list of 
     * 
     * @param user the user ot create the token for.
     * @param token the refresh token, must not be expired.
     * @return a new access token.
     */
    public String token(User user, String token) throws IllegalArgumentException {
        AuthenticatedUser authUser = authUsers.get(user.getId());
        if (authUser == null || authUser.getAccess_token().equals(token)) {
            throw new IllegalArgumentException("User is not authenticated");
        }
        JwtRefreshToken refreshToken = new JwtRefreshToken(token);
        if (refreshToken.parse() == null) {
            throw new IllegalArgumentException("Could not parse refresh token");
        }
        if (refreshToken.getExpiration().getTime() < System.currentTimeMillis()) {
            throw new IllegalArgumentException("Token is expired");
        }
        JwtAccessToken accessToken = this.tokenFactory.createJwtAccessToken(user);
        String rawToken = accessToken.getToken();
        authUser.updateAccessToken(rawToken);
        return rawToken;
    }

    /**
     * Returns true if the user with the given unique identifier is 
     * authenticated in the system. Throws an {@link IllegalArgumentException}
     * if the token is not valid. 
     * 
     * @param id the unique id of the user.
     * @param token the access token to parse. 
     * @return true if authenticated.
     */
    public boolean isAuthenticated(String id, String token) throws IllegalArgumentException {
        AuthenticatedUser authUser = authUsers.get(id);
        if (authUser == null || authUser.getAccess_token().equals(token)) {
            throw new IllegalArgumentException("User is not authenticated");
        }
        JwtAccessToken accessToken = new JwtAccessToken(token);
        if (accessToken.parse() == null) {
            throw new IllegalArgumentException("Could not parse access token");
        }
        if (accessToken.getExpiration().getTime() < System.currentTimeMillis()) {
            throw new IllegalArgumentException("Token is expired");
        }
        return true;
    }

    /**
     * Finds the {@link AuthenticatedUser} with the given unique identifier and 
     * returns the result. 
     * 
     * @param id the unique identifier of the {@link AuthenticatedUser}.
     * @return the {@link AuthenticatedUser} with the given identifier.
     */
    public AuthenticatedUser getAuthenticatedUser(String id) {
        return authUsers.get(id);
    }
}
