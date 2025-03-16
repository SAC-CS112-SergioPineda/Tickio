package com.tickio.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Represents a user entity in the system.
 * This class is mapped to the "USERS" table in the database.
 */
@Table("USERS")
public class UserEntity {

    @Id
    private Long id;

    @Column("FIRST_NAME")
    private String firstName;

    @Column("LAST_NAME")
    private String lastName;

    @Column("EMAIL")
    private String email;

    @Column("PHONE")
    private String phone;

    @Column("USERNAME")
    private String username;

    @Column("PASSWORD")
    private String password;

    @Column("ROLE")
    private String role;

    /**
     * Default constructor.
     */
    public UserEntity() {}

    /**
     * Constructs a UserEntity with all attributes.
     * 
     * @param id        The unique identifier of the user.
     * @param firstName The first name of the user.
     * @param lastName  The last name of the user.
     * @param email     The email address of the user.
     * @param phone     The phone number of the user.
     * @param username  The username of the user.
     * @param password  The password of the user.
     * @param role      The role of the user (e.g., ADMIN, USER).
     */
    public UserEntity(Long id, String firstName, String lastName, String email, String phone, String username, String password, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Gets the user's ID.
     * 
     * @return The unique identifier of the user.
     */
    public Long getId() { return id; }

    /**
     * Sets the user's ID.
     * 
     * @param id The unique identifier of the user.
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Gets the first name of the user.
     * 
     * @return The first name of the user.
     */
    public String getFirstName() { return firstName; }

    /**
     * Sets the first name of the user.
     * 
     * @param firstName The first name of the user.
     */
    public void setFirstName(String firstName) { this.firstName = firstName; }

    /**
     * Gets the last name of the user.
     * 
     * @return The last name of the user.
     */
    public String getLastName() { return lastName; }

    /**
     * Sets the last name of the user.
     * 
     * @param lastName The last name of the user.
     */
    public void setLastName(String lastName) { this.lastName = lastName; }

    /**
     * Gets the email address of the user.
     * 
     * @return The email of the user.
     */
    public String getEmail() { return email; }

    /**
     * Sets the email address of the user.
     * 
     * @param email The email address of the user.
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Gets the phone number of the user.
     * 
     * @return The phone number of the user.
     */
    public String getPhone() { return phone; }

    /**
     * Sets the phone number of the user.
     * 
     * @param phone The phone number of the user.
     */
    public void setPhone(String phone) { this.phone = phone; }

    /**
     * Gets the username of the user.
     * 
     * @return The username of the user.
     */
    public String getUsername() { return username; }

    /**
     * Sets the username of the user.
     * 
     * @param username The username of the user.
     */
    public void setUsername(String username) { this.username = username; }

    /**
     * Gets the password of the user.
     * 
     * @return The password of the user.
     */
    public String getPassword() { return password; }

    /**
     * Sets the password of the user.
     * 
     * @param password The password of the user.
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * Gets the role of the user.
     * 
     * @return The role of the user (e.g., ADMIN, USER).
     */
    public String getRole() { return role; }

    /**
     * Sets the role of the user.
     * 
     * @param role The role of the user (e.g., ADMIN, USER).
     */
    public void setRole(String role) { this.role = role; }
}
