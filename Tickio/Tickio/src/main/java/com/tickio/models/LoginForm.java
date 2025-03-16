package com.tickio.models;

import javax.validation.constraints.NotBlank;

/**
 * Represents the login form used for authentication.
 * This class captures the username and password input from users.
 */
public class LoginForm {

    /** The username provided by the user for authentication. */
    @NotBlank(message = "Username is required.")
    private String username;

    /** The password provided by the user for authentication. */
    @NotBlank(message = "Password is required.")
    private String password;

    /**
     * Gets the username entered in the login form.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for the login form.
     *
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password entered in the login form.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for the login form.
     *
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
