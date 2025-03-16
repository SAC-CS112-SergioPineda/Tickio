package com.tickio.data.repository;

import com.tickio.data.entity.TicketEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

/**
 * Repository interface for managing {@link TicketEntity} persistence.
 * Extends {@link CrudRepository} to provide CRUD operations.
 */
public interface TicketRepository extends CrudRepository<TicketEntity, Long> {
    
    /**
     * Retrieves a list of tickets associated with a specific user.
     *
     * @param userId The ID of the user whose tickets are to be fetched.
     * @return A list of {@link TicketEntity} objects belonging to the specified user.
     */
    List<TicketEntity> findByUserId(Long userId);
}
