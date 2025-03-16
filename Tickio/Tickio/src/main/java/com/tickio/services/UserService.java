package com.tickio.services;

import org.springframework.stereotype.Service;
import java.util.Optional;
import com.tickio.data.entity.UserEntity;
import com.tickio.data.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserEntity> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean registerUser(String firstName, String lastName, String email, String phone, String username, String password) 
    {
        if (userRepository.findByUsername(username).isPresent() || userRepository.findByEmail(email).isPresent()) {
            return false; // Username or Email already exists
    }

        UserEntity newUser = new UserEntity(null, firstName, lastName, email, phone, username, password, "USER");
        userRepository.save(newUser);
        return true;
    }
}

