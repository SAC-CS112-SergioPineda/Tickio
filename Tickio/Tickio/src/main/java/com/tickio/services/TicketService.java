package com.tickio.services;

import com.tickio.data.entity.TicketEntity;
import com.tickio.data.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing tickets.
 * This class provides methods for creating, retrieving, updating, and deleting tickets.
 */
@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    /**
     * Constructs a new TicketService with the given TicketRepository.
     *
     * @param ticketRepository The repository used for ticket data operations.
     */
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    /**
     * Retrieves all tickets associated with a specific user.
     *
     * @param userId The ID of the user.
     * @return A list of tickets belonging to the specified user.
     */
    public List<TicketEntity> getUserTickets(Long userId) {
        return ticketRepository.findByUserId(userId);
    }

    /**
     * Creates a new ticket for a user.
     *
     * @param userId      The ID of the user creating the ticket.
     * @param title       The title of the ticket.
     * @param description The description of the issue.
     * @param priority    The priority level of the ticket.
     * @return The newly created TicketEntity.
     */
    public TicketEntity createTicket(Long userId, String title, String description, String priority) {
        TicketEntity ticket = new TicketEntity(userId, title, description, priority);
        return ticketRepository.save(ticket);
    }

    /**
     * Finds a ticket by its ID.
     *
     * @param id The ID of the ticket.
     * @return The TicketEntity if found, otherwise null.
     */
    public TicketEntity findById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    /**
     * Updates an existing ticket.
     *
     * @param ticket The ticket entity with updated information.
     * @return The updated TicketEntity.
     */
    public TicketEntity updateTicket(TicketEntity ticket) {
        return ticketRepository.save(ticket);
    }

    /**
     * Deletes a ticket by its ID.
     *
     * @param id The ID of the ticket to be deleted.
     */
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    /**
     * Creates a new ticket entity, ensuring a valid user ID is provided.
     *
     * @param ticket The ticket entity to create.
     * @return The created TicketEntity.
     * @throws IllegalArgumentException if the user ID is null.
     */
    public TicketEntity createTicket(TicketEntity ticket) {
        if (ticket.getUserId() == null) {
            throw new IllegalArgumentException("User ID cannot be null when creating a ticket.");
        }
        return ticketRepository.save(ticket);
    }

    /**
     * Retrieves all tickets stored in the repository.
     *
     * @return A list of all TicketEntity objects.
     */
    public List<TicketEntity> getAllTickets() {
        List<TicketEntity> tickets = new ArrayList<>();
        ticketRepository.findAll().forEach(tickets::add);
        return tickets;
    }
}
