package com.tickio.data.repository;

import com.tickio.data.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

/**
 * Repository interface for managing {@link UserEntity} persistence.
 * Extends {@link CrudRepository} to provide CRUD operations.
 */
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    
    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user.
     * @return An {@link Optional} containing the {@link UserEntity} if found, otherwise empty.
     */
    Optional<UserEntity> findByUsername(String username);
    
    /**
     * Retrieves a user by their email address.
     *
     * @param email The email address of the user.
     * @return An {@link Optional} containing the {@link UserEntity} if found, otherwise empty.
     */
    Optional<UserEntity> findByEmail(String email);
}
