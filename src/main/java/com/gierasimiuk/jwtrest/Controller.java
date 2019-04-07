package com.gierasimiuk.jwtrest;

import java.util.HashMap;

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
    private static final JWTHandler handler = new JWTHandler();

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
        String existing = users.get(username);
        if (existing.equals(password)) {
            return new ResponseEntity<>(handler.create(username), HttpStatus.OK);
        }
        return new ResponseEntity<>("Incorrect password", HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/refresh", method = {RequestMethod.POST })
    public @ResponseBody ResponseEntity<Object> refresh( 
        @RequestParam("username") String username,
        @RequestParam("token") String token) {
        return new ResponseEntity<>(handler.refresh(username, token), HttpStatus.OK);
    }

    @RequestMapping("/access")
    public @ResponseBody ResponseEntity<Object> access(
            @RequestParam("token") String token) {
        try {
            if (handler.verify(token)) {
                return new ResponseEntity<>("Access Granted!", HttpStatus.OK);
            }
            return new ResponseEntity<>("Access Denied!", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>("Could not verify JWT token", HttpStatus.BAD_REQUEST);
        }
    }
}
