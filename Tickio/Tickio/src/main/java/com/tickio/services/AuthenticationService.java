package com.tickio.services;

import org.springframework.stereotype.Service;

/**
 * Service class for handling user authentication.
 * This class provides a simple authentication mechanism with hardcoded credentials.
 */
@Service
public class AuthenticationService {

    /** Hardcoded valid username for authentication. */
    private static final String VALID_USERNAME = "admin";

    /** Hardcoded valid password for authentication. */
    private static final String VALID_PASSWORD = "password";

    /**
     * Authenticates a user by checking the provided username and password 
     * against hardcoded credentials.
     *
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     * @return {@code true} if the credentials match, {@code false} otherwise.
     */
    public boolean authenticate(String username, String password) {
        return VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password);
    }
}
