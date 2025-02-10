package com.tickio.services;

import com.tickio.models.Ticket;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service  
public class TicketService {

    private final List<Ticket> ticketList = new ArrayList<>();
    private Long ticketIdCounter = 1L; // Simulated database auto-increment

    public List<Ticket> getAllTickets() {
        return ticketList;
    }

    public void addTicket(Ticket ticket) {
        ticket.setId(ticketIdCounter++);
        ticketList.add(ticket);
    }
}
