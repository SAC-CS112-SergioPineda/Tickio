package com.tickio.services;

import com.tickio.data.entity.TicketEntity;
import com.tickio.data.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    
    // Find ticket by ID
    public TicketEntity findById(Long id)
    {
    	return ticketRepository.findById(id).orElse(null);
    }
    
    // Update existing ticket
    public TicketEntity updateTicket(TicketEntity ticket)
    {
    	return ticketRepository.save(ticket);
    }
    
    // Delete ticket by ID
    public void deleteTicket(Long id)
    {
    	ticketRepository.deleteById(id);
    }
    
    public TicketEntity createTicket(TicketEntity ticket) {
        if (ticket.getUserId() == null) {
            throw new IllegalArgumentException("User ID cannot be null when creating a ticket.");
        }
        return ticketRepository.save(ticket);
    }
    
    public List<TicketEntity> getAllTickets() {
        List<TicketEntity> tickets = new ArrayList<>();
        ticketRepository.findAll().forEach(tickets::add);
        return tickets;
    }


}

