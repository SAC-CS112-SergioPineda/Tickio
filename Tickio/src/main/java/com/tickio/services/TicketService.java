package com.tickio.services;

import com.tickio.data.entity.TicketEntity;
import com.tickio.data.repository.TicketRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    // Retrieve all tickets for a specific user
    public List<TicketEntity> getUserTickets(Long userId) {
        return ticketRepository.findByUserId(userId);
    }

    // Create a new ticket for a user
    public TicketEntity createTicket(Long userId, String title, String description, String priority) {
        TicketEntity ticket = new TicketEntity(userId, title, description, priority);
        return ticketRepository.save(ticket);
    }
}

