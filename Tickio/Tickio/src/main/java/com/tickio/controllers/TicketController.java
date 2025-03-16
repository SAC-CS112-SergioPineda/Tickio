package com.tickio.controllers;

import com.tickio.data.entity.TicketEntity;
import com.tickio.services.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Controller for managing tickets.
 * Provides endpoints for creating, updating, deleting, and viewing tickets.
 */
@Controller
@RequestMapping("/tickets") 
public class TicketController {

    private final TicketService ticketService;

    /**
     * Constructs a TicketController with the specified TicketService.
     *
     * @param ticketService The service used to manage tickets.
     */
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /**
     * Lists all tickets for the logged-in user.
     * Admin users see all tickets, while regular users see only their own.
     *
     * @param session The HTTP session containing user authentication data.
     * @param model   The model to store ticket data.
     * @return The view name for displaying tickets.
     */
    @GetMapping
    public String listUserTickets(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login"; // Redirect if not logged in
        }

        String role = (String) session.getAttribute("role");
        List<TicketEntity> tickets;

        if ("ADMIN".equalsIgnoreCase(role)) {
            tickets = ticketService.getAllTickets();
        } else {
            tickets = ticketService.getUserTickets(userId);
        }

        model.addAttribute("tickets", tickets);
        return "tickets";
    }

    /**
     * Displays the "Create Ticket" form.
     *
     * @param model The model to hold the ticket form data.
     * @return The view name for the ticket creation form.
     */
    @GetMapping("/new")
    public String showCreateTicketForm(Model model) {
        model.addAttribute("ticket", new TicketEntity());
        return "create_ticket";
    }

    /**
     * Handles form submission for creating a new ticket.
     *
     * @param ticket  The ticket entity containing user inputs.
     * @param session The HTTP session containing user authentication data.
     * @return Redirects to the ticket list after successful creation.
     */
    @PostMapping
    public String createTicket(@ModelAttribute TicketEntity ticket, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";

        ticket.setUserId(userId);
        ticketService.createTicket(ticket.getUserId(), ticket.getTitle(), ticket.getDescription(), ticket.getPriority());

        return "redirect:/tickets";
    }

    /**
     * Displays the edit form for a specific ticket.
     *
     * @param id    The ID of the ticket to edit.
     * @param model The model to store ticket data.
     * @return The view name for the ticket edit form.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        TicketEntity ticket = ticketService.findById(id);
        if (ticket == null) return "redirect:/tickets";

        model.addAttribute("ticket", ticket);
        return "edit_ticket";
    }

    /**
     * Handles ticket updates.
     * Admin users can modify any field, while regular users cannot change the status.
     *
     * @param ticket            The updated ticket entity.
     * @param redirectAttributes Attributes for redirecting with messages.
     * @param session           The HTTP session containing user role data.
     * @return Redirects to the ticket list after updating.
     */
    @PostMapping("/update")
    public String updateTicket(@ModelAttribute TicketEntity ticket,
                               RedirectAttributes redirectAttributes,
                               HttpSession session) {
        String role = (String) session.getAttribute("role");
        if (!"ADMIN".equalsIgnoreCase(role)) {
            TicketEntity originalTicket = ticketService.findById(ticket.getId());
            if (originalTicket != null) {
                ticket.setStatus(originalTicket.getStatus());
            }
        }

        ticketService.updateTicket(ticket);
        redirectAttributes.addFlashAttribute("message", "Ticket #" + ticket.getId() + " has been updated.");
        return "redirect:/tickets";
    }

    /**
     * Handles ticket deletion.
     * Only admins can delete tickets.
     *
     * @param id      The ID of the ticket to delete.
     * @param session The HTTP session containing user role data.
     * @return Redirects to the ticket list after deletion.
     */
    @GetMapping("/delete/{id}")
    public String deleteTicket(@PathVariable Long id, HttpSession session) {
        String role = (String) session.getAttribute("role");
        if (!"ADMIN".equalsIgnoreCase(role)) {
            return "redirect:/tickets";
        }
        ticketService.deleteTicket(id);
        return "redirect:/tickets";
    }

    /**
     * Displays details for a specific ticket.
     * Admin users can view any ticket, while regular users can only view their own.
     *
     * @param id      The ID of the ticket.
     * @param session The HTTP session containing user authentication data.
     * @param model   The model to store ticket data.
     * @return The view name for displaying ticket details.
     */
    @GetMapping("/{id}")
    public String viewTicketDetails(@PathVariable Long id, HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login"; // Redirect if not logged in
        }

        String role = (String) session.getAttribute("role");
        TicketEntity ticket;

        if ("ADMIN".equalsIgnoreCase(role)) {
            ticket = ticketService.findById(id);
        } else {
            ticket = ticketService.findById(id);
            if (ticket == null || !ticket.getUserId().equals(userId)) {
                return "redirect:/tickets"; // Access denied
            }
        }

        model.addAttribute("ticket", ticket);
        return "ticket_details";
    }
}
