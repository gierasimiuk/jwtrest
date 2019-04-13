package com.gierasimiuk.jwtrest.controller;

import com.gierasimiuk.jwtrest.model.AuthenticatedUser;
import com.gierasimiuk.jwtrest.model.User;
import com.gierasimiuk.jwtrest.service.AuthService;
import com.gierasimiuk.jwtrest.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controller for routing HTTP endpoints accordingly. 
 * 
 * @author Michael Gierasimiuk
 */
@RestController
public class AuthController {
    
    private static final AuthService authService = new AuthService();
    private static final UserService userService = new UserService();

    /**
     * Endpoint to process a signup action.
     * 
     * @param username the user's username. 
     * @param password the user's password.
     * @return the {@link ResponseBody} to send back to the client.
     */
    @RequestMapping(value = "api/auth/signup", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Object> signup(
            @RequestHeader("username") String username, 
            @RequestHeader("password") String password) {
        User user;
        try {
            user = userService.signup(username, password);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Endpoint to process a login action.
     * 
     * @param username the user's username. 
     * @param password the user's password.
     * @return the {@link ResponseBody} to send back to the client. 
     */
    @RequestMapping(value = "api/auth/login", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Object> login(
            @RequestHeader("id") String id, 
            @RequestHeader("username") String username, 
            @RequestHeader("password") String password) {
        AuthenticatedUser authUser;
        try {
            User user = userService.getUser(id);
            if (user == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not find user with id " + id);
            }
            authUser = authService.login(user, username, password);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return new ResponseEntity<>(authUser, HttpStatus.OK);
    }

    /**
     * Endpoint to process a token refresh.
     * 
     * @param username the user's unique identifier. 
     * @param token the refresh token.
     * @return the {@link ResponseBody} to send back to the client. 
     */
    @RequestMapping(value = "api/auth/token", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Object> refresh( 
            @RequestHeader("username") String username, 
            @RequestHeader("token") String token) {
    	try {
    		String newToken = authService.token(username, token);
    		return new ResponseEntity<>(newToken, HttpStatus.OK);
    	} catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Endpoint to test user access by exposing content only if the user has been 
     * successfully authenticated with the server.
     * 
     * 200 with an "Access granted" string if user is authenticated.
     * 401 with an "Access denied" string if user is not authenticated.
     * 
     * @param token the access token. 
     * @return the {@link ResponseBody} to send back to the client.
     */
    @RequestMapping(value="/api/access", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Object> access(
            @RequestHeader("token") String token) {
        return new ResponseEntity<>("Access Granted!", HttpStatus.OK);
    }
}
