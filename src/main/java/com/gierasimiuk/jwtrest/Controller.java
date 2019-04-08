package com.gierasimiuk.jwtrest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private static final HashMap<String, String> users = new HashMap<String, String>();
    private static final AuthService handler = new AuthService();

    @RequestMapping(value = "/signup", method = {RequestMethod.POST })
    public @ResponseBody ResponseEntity<Object> signup(
            @RequestParam("username") String username, 
            @RequestParam("password") String password, 
            HttpServletRequest request) {
        if (users.containsKey(username)) {
            return new ResponseEntity<>("User '" + username + "' already exists", HttpStatus.CONFLICT);
        }
        users.put(username, password);
        return new ResponseEntity<>("User '" + username + "' created", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST })
    public @ResponseBody ResponseEntity<Object> login(
            @RequestParam("username") String username, 
            @RequestParam("password") String password, 
            HttpServletRequest request) {
        if (!users.containsKey(username)) {
            return new ResponseEntity<>("User '" + username + "' not found", HttpStatus.NOT_FOUND);
        }
        try {
            String existing = users.get(username);
            if (existing.equals(password)) {
                Map<String, String> tokens = handler.auth(username);
                String access = tokens.get("access_token");
                String refresh = tokens.get("refresh_token");
                String response = "{ access_token: " + access + ", refresh_token: " + refresh + " }";
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>("Incorrect password", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>("Could not verify refresh token", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/refresh", method = {RequestMethod.POST })
    public @ResponseBody ResponseEntity<Object> refresh( 
            @RequestParam("username") String username,
            @RequestParam("token") String token) {
        if (!users.containsKey(username)) {
            return new ResponseEntity<>("User '" + username + "' not found", HttpStatus.NOT_FOUND);
        }
        try {
            if (handler.isRefreshTokenValid(username, token)) {
                String accessToken = handler.refresh(username, token);
                return new ResponseEntity<>(accessToken, HttpStatus.OK);
            }
            return new ResponseEntity<>("Access Denied!", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>("Could not verify refresh token", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value="/access", method = {RequestMethod.GET })
    public @ResponseBody ResponseEntity<Object> access(
            @RequestParam("token") String token) {
        try {
            if (handler.isAccessToken(token) && handler.verifyExp(token)) {
                return new ResponseEntity<>("Access Granted!", HttpStatus.OK);
            }
            return new ResponseEntity<>("Access Denied!", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>("Could not verify access token", HttpStatus.BAD_REQUEST);
        }
    }
}
