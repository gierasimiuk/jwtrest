package com.gierasimiuk.jwtrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Basic RESTful server with JWT authentication.
 * 
 * @author Michael Gierasimiuk
 */
@SpringBootApplication
public class JwtRestApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(JwtRestApplication.class, args);
	}
}
