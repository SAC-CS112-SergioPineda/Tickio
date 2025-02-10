package com.tickio.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final Map<String, String> userDatabase = new HashMap<>(); // Store users

    // Predefined admin user
    public AuthenticationService() {
        userDatabase.put("admin", "password");
    }

    public boolean authenticate(String username, String password) {
        System.out.println("Attempting login with: " + username + " / " + password);
        return userDatabase.containsKey(username) && userDatabase.get(username).equals(password);
    }

    public void registerUser(String username, String password) {
        userDatabase.put(username, password);
    }
}
