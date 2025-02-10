package com.tickio.controllers;

import com.tickio.models.Ticket;
import com.tickio.services.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class TicketController {

    private final TicketService ticketService; // Declare service instance

    // Constructor-based Dependency Injection
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // Display the list of submitted tickets
    @GetMapping("/tickets")
    public String showTickets(Model model) {
        List<Ticket> tickets = ticketService.getAllTickets(); // Fetch tickets
        
     // Format date for each ticket
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (Ticket ticket : tickets) {
            ticket.setFormattedDate(ticket.getDateSubmitted().format(formatter));
        }
        
        model.addAttribute("tickets", tickets);
        return "tickets"; // Thymeleaf template path
    }

    // Show ticket creation form
    @GetMapping("/tickets/new")
    public String showCreateTicketForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "create_ticket"; // Thymeleaf form template
    }

    // Handle new ticket submission
    @PostMapping("/tickets")
    public String createTicket(@ModelAttribute Ticket ticket) {
        ticketService.addTicket(ticket); // Save ticket
        return "redirect:/tickets"; // Redirect to ticket list
    }
}
