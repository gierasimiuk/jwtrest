package com.gierasimiuk.jwtrest.model;

public class Token {
    public static final String TYPE_ACCESS = "access_token";
    public static final String TYPE_REFRESH = "refresh_token";

    private String type;
    private String token;

    public Token(String token, String type) {
        this.token = token;
        this.type = type;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
    }
}