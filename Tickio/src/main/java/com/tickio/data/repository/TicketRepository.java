package com.tickio.data.repository;

import com.tickio.data.entity.TicketEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface TicketRepository extends CrudRepository<TicketEntity, Long> {
    
    // Fetch all tickets for a specific user
    List<TicketEntity> findByUserId(Long userId);
}
