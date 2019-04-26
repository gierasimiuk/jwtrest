package com.gierasimiuk.jwtrest.model;

import java.util.UUID;

/**
 * {@link User} class.
 * 
 * @author Michael Gierasimiuk
 */
public class User {

    private String id;
    private String username;
    private String password;

    public User() { 
        this.id = UUID.randomUUID().toString();
    }

    public User(String username, String password) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
    }

    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public String getId() {
		return this.id;
    }

	public String getUsername() {
		return this.username;
	}
    
    public String getPassword() {
		return this.password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "ID: " + this.id 
            + ", Username: " + this.username 
            + ", Password: " + this.password;
    }
}