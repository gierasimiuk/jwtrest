package com.gierasimiuk.jwtrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Utilise Factory pattern. 
 * Utilise middleware to handle authentication. 
 */
@SpringBootApplication
public class JwtRestApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(JwtRestApplication.class, args);
	}
}
