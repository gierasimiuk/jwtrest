package com.gierasimiuk.jwtrest.model;

import java.util.UUID;

public class User {

    private String id;
    private String username;
    private String password;

    public User(String username, String password) {
        this.id = UUID.randomUUID().toString();
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
}