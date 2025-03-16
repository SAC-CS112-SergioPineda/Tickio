package com.tickio.services;

import org.springframework.stereotype.Service;
import java.util.Optional;
import com.tickio.data.entity.UserEntity;
import com.tickio.data.repository.UserRepository;

/**
 * Service class for managing user-related operations.
 * This class provides methods for retrieving and registering users.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * Constructs a new UserService with the given UserRepository.
     *
     * @param userRepository The repository used for user data operations.
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user to find.
     * @return An Optional containing the UserEntity if found, otherwise empty.
     */
    public Optional<UserEntity> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Registers a new user if the username and email are not already taken.
     *
     * @param firstName The first name of the user.
     * @param lastName  The last name of the user.
     * @param email     The email address of the user.
     * @param phone     The phone number of the user.
     * @param username  The desired username.
     * @param password  The desired password.
     * @return true if registration is successful, false if the username or email already exists.
     */
    public boolean registerUser(String firstName, String lastName, String email, String phone, String username, String password) {
        if (userRepository.findByUsername(username).isPresent() || userRepository.findByEmail(email).isPresent()) {
            return false; // Username or Email already exists
        }

        UserEntity newUser = new UserEntity(null, firstName, lastName, email, phone, username, password, "USER");
        userRepository.save(newUser);
        return true;
    }
}
