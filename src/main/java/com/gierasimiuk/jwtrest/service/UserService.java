package com.gierasimiuk.jwtrest.service;

import java.util.HashMap;
import java.util.Map;

import com.gierasimiuk.jwtrest.model.User;

import org.springframework.util.StringUtils;

/**
 * Handles user creation and validation. 
 */
public class UserService {
    
    private final Map<String, User> users;
    private final Map<String, User> usernames;

    /**
     * Creates a new {@link UserService}. 
     */
    public UserService() {
        users = new HashMap<String, User>();
        usernames = new HashMap<String, User>();
    }

    /**
     * Creates a new user. 
     * 
     * @param username the user's username. 
     * @param password the user's password.
     * @return the {@link User} the new user. 
     */
    public User signup(String username, String password) {

        if (StringUtils.hasText(username) == false) {
            throw new IllegalArgumentException("Cannot create user without 'username' field");
        }

        if (StringUtils.hasText(password) == false) {
            throw new IllegalArgumentException("Cannot create user without 'password' field");
        }

        if (usernames.containsKey(username)) {
            throw new IllegalArgumentException("User '" + username + "' already exists");
        }

        User user = new User(username, password);

        users.put(user.getId(), user);
        usernames.put(user.getUsername(), user);
        
        return user;
    }

    public User getUser(String id) {
        return users.get(id);
    }

    public User getUserByUsername(String id) {
        return usernames.get(id);
    }

    // public AuthenticatedUser getAuthUser(String id) {
    //     return authUsers.get(id);
    // }
}
