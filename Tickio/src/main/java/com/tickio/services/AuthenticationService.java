package com.tickio.services;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

	// hardcoded credentials for now
	private static final String VALID_USERNAME = "admin";
	private static final String VALID_PASSWORD = "password";
	
	public boolean authenticate(String username, String password)
	{
		return VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password);
	}
}
