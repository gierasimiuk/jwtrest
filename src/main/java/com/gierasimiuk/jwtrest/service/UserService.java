package com.gierasimiuk.jwtrest.service;

import java.util.HashMap;
import java.util.Map;

import com.gierasimiuk.jwtrest.model.User;

import org.springframework.util.StringUtils;

/**
 * {@link UserService} service for handling users.
 * 
 * @author Michael Gierasimiuk
 */
public class UserService {
    
    private final Map<String, User> users;
    private final Map<String, User> usernames;

    /**
     * Creates a new {@link UserService}. 
     */
    public UserService() {
        this.users = new HashMap<String, User>();
        this.usernames = new HashMap<String, User>();
    }

    /**
     * Creates a new user. 
     * 
     * @param username the user's username. 
     * @param password the user's password.
     * @return the {@link User} the new user. 
     */
    public User signup(User user) {

        if (!StringUtils.hasText(user.getUsername())) {
            throw new IllegalArgumentException(
                "Cannot create user without 'username' field");
        }
        
        if (!StringUtils.hasText(user.getPassword())) {
            throw new IllegalArgumentException(
                "Cannot create user without 'password' field");
        }
        
        if (usernames.containsKey(user.getUsername())) {
            throw new IllegalArgumentException(
                "User '" + user.getUsername() + "' already exists");
        }
        
        users.put(user.getId(), user);
        usernames.put(user.getUsername(), user);
        return user;
    }
    
    /**
     * Finds the {@link User} with the given unique identifier and returns the 
     * result. Result can be null if no user is found.
     * 
     * @param id the id.
     * @return the {@link User} found or null.
     */
    public User getUser(String id) {
        return users.get(id);
    }
    
    /**
     * Finds the {@link User} with the given username and returns the result. 
     * Result can be null if nouser is found.
     * 
     * @param username the username.
     * @return the {@link User} found or null.
     */
    public User getUserByUsername(String username) {
        return usernames.get(username);
    }
}
