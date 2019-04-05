package com.gierasimiuk.jwtrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JWTRestApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(JWTRestApplication.class, args);
		System.out.println("Starting server ...");
		
		JWTHandler handler = new JWTHandler();
		String jwt = handler.createJWT("test", "Me!", "Subject", 60000);
		handler.parseJWT(jwt);
	}
}
