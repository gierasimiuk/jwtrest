package com.gierasimiuk.jwtrest.controller;

import com.gierasimiuk.jwtrest.model.AuthenticatedUser;
import com.gierasimiuk.jwtrest.model.User;
import com.gierasimiuk.jwtrest.model.UserToken;
import com.gierasimiuk.jwtrest.service.AuthService;
import com.gierasimiuk.jwtrest.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
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
public class Controller {
    
    private static final AuthService authService = new AuthService();
    private static final UserService userService = new UserService();

    /**
     * Endpoint to process a signup action.
     * 
     * @param user the user sent as part of the request.
     * @return the {@link ResponseBody} to send back to the client.
     */
    @RequestMapping(value = "api/users/signup", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Object> signup(@RequestBody User user) {
        try {
            userService.signup(user);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                e.getMessage());
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Endpoint to process a login action.
     * 
     * @param user the user sent as part of the request.
     * @return the {@link ResponseBody} to send back to the client. 
     */
    @RequestMapping(value = "api/users/login", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Object> login(@RequestBody User user) {
        AuthenticatedUser authUser;
        try {
            User found = userService.getUser(user.getId());
            if (found == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                    "Could not find user with id " + user.getId());
            }
            authUser = authService.login(found, user);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                e.getMessage());
        }
        return new ResponseEntity<>(authUser, HttpStatus.OK);
    }

    /**
     * Endpoint to process a token refresh. Returns a new access token to the 
     * client assuming a valid refresh token was passed through.
     * 
     * @param user the user id and refresh token combination.
     * @return the {@link ResponseBody} to send back to the client.
     */
    @RequestMapping(value = "api/auth/token", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Object> refresh(@RequestBody UserToken user) {
    	try {
            User found = userService.getUser(user.getId());
            if (found == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                    "Could not find user with id " + user.getId());
            }
            String token = user.getToken();
    		String accessToken = authService.token(found, token);
    		return new ResponseEntity<>(accessToken, HttpStatus.OK);
    	} catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                e.getMessage());
        }
    }

    /**
     * Endpoint to test user authentication by returning an "Access Granted!"
     * string if the user sends through a valid token.
     * 
     * 200 with an "Access granted" string if user is authenticated.
     * 401 with an "Access denied" string if user is not authenticated.
     * 
     * @param user the user id and access token combination.
     * @return the {@link ResponseBody} to send back to the client.
     */
    @RequestMapping(value="/api/access", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Object> access(@RequestBody UserToken user) {
        try {
            User found = userService.getUser(user.getId());
            if (found == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                    "Could not find user with id " + user.getId());
            }
            if (authService.isAuthenticated(user.getId(), user.getToken())) {
                return new ResponseEntity<>("Access Granted!", HttpStatus.OK);
            }
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                e.getMessage());
        }
        return new ResponseEntity<>("Access Denied!", HttpStatus.UNAUTHORIZED);
    }
}
