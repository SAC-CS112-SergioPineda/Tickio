package com.tickio.controllers;

import com.tickio.data.entity.TicketEntity;
import com.tickio.services.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class DashboardController {

    private final TicketService ticketService;

    public DashboardController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/login"; // Redirect if not logged in
        }

        // Retrieve the tickets for the logged-in user
        List<TicketEntity> tickets = ticketService.getUserTickets(userId);

        // Count tickets by status
        long openTickets = tickets.stream()
                .filter(ticket -> "Open".equalsIgnoreCase(ticket.getStatus()))
                .count();

        long resolvedTickets = tickets.stream()
                .filter(ticket -> "Closed".equalsIgnoreCase(ticket.getStatus()))
                .count();

        long pendingTickets = tickets.stream()
                .filter(ticket -> "In Progress".equalsIgnoreCase(ticket.getStatus()))
                .count();

        // Add counts to the model
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("openTickets", openTickets);
        model.addAttribute("resolvedTickets", resolvedTickets);
        model.addAttribute("pendingTickets", pendingTickets);

        return "dashboard"; // Loads dashboard.html
    }
}
