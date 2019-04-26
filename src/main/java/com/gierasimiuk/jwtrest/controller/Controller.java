package com.gierasimiuk.jwtrest.controller;

import com.gierasimiuk.jwtrest.model.AuthenticatedUser;
import com.gierasimiuk.jwtrest.model.User;
import com.gierasimiuk.jwtrest.model.UserAccessToken;
import com.gierasimiuk.jwtrest.model.UserRefreshToken;
import com.gierasimiuk.jwtrest.service.AuthService;
import com.gierasimiuk.jwtrest.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controller for routing HTTP requests.
 * 
 * @author Michael Gierasimiuk
 */
@RestController
public class Controller {
    
    private static final AuthService authService = new AuthService();
    private static final UserService userService = new UserService();

    /**
     * Endpoint to process a signup.
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
     * Endpoint to process a login.
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
     * client only if a valid refresh token is passed through.
     * 
     * @param user the user containing id and refresh token.
     * @return the {@link ResponseBody} to send back to the client.
     */
    @RequestMapping(value = "api/auth/token", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Object> refresh(@RequestBody UserRefreshToken user) {
    	try {
            User found = userService.getUser(user.getUser_id());
            if (found == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                    "Could not find user with id " + user.getUser_id());
            }
            String token = user.getRefresh_token();
            String accessTokenRaw = authService.token(found, token);
            UserAccessToken accessToken = new UserAccessToken(user.getUser_id(), accessTokenRaw);
    		return new ResponseEntity<>(accessToken, HttpStatus.OK);
    	} catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                e.getMessage());
        }
    }

    /**
     * Endpoint to test user authentication.
     * 
     * 200 with an "Access granted" string if user is authenticated.
     * 401 with an "Access denied" string if user is not authenticated.
     * 
     * @param user the user containing id and refresh token.
     * @return the {@link ResponseBody} to send back to the client.
     */
    @RequestMapping(value="/api/access", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Object> access(@RequestBody UserAccessToken user) {
        try {
            User found = userService.getUser(user.getUser_id());
            if (found == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                    "Could not find user with id " + user.getUser_id());
            }
            if (authService.isAuthenticated(user.getUser_id(), user.getAccess_token())) {
                return new ResponseEntity<>("Access Granted!", HttpStatus.OK);
            }
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                e.getMessage());
        }
        return new ResponseEntity<>("Access Denied!", HttpStatus.UNAUTHORIZED);
    }
}
