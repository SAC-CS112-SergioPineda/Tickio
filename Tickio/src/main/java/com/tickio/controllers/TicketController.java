package com.tickio.controllers;

import com.tickio.data.entity.TicketEntity;
import com.tickio.services.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tickets")  // ✅ This ensures all endpoints start with /tickets
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // ✅ List all tickets for the logged-in user
    @GetMapping
    public String listUserTickets(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/login"; // Redirect if not logged in

        List<TicketEntity> tickets = ticketService.getUserTickets(userId);
        
        if (tickets == null) { // ✅ Fix: Ensure Thymeleaf never gets null
            tickets = new ArrayList<>();
        }

        model.addAttribute("tickets", tickets);
        return "tickets"; // ✅ Ensure this matches the filename tickets.html
    }


    // ✅ Display the "Create Ticket" form
    @GetMapping("/new")
    public String showCreateTicketForm(Model model) {
        model.addAttribute("ticket", new TicketEntity());
        return "create_ticket"; // ✅ Ensure this matches create_ticket.html
    }

    // ✅ Handle form submission for creating a ticket
    @PostMapping
    public String createTicket(@ModelAttribute TicketEntity ticket, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";

        ticketService.createTicket(userId, ticket.getTitle(), ticket.getDescription(), ticket.getPriority());
        return "redirect:/tickets"; // ✅ Redirects back to tickets list
    }
}
