package com.tickio.controllers;

import com.tickio.data.entity.TicketEntity;
import com.tickio.services.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    //  List all tickets for the logged-in user
    @GetMapping
    public String listUserTickets(HttpSession session, Model model) {
    	Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login"; // Redirect if not logged in
        }
        
        String role = (String) session.getAttribute("role");
        List<TicketEntity> tickets;
        
        // If admin, show all tickets; otherwise, only the user's tickets.
        if ("ADMIN".equalsIgnoreCase(role)) {
            tickets = ticketService.getAllTickets();
        } else {
            tickets = ticketService.getUserTickets(userId);
        }
        
        model.addAttribute("tickets", tickets);
        return "tickets"; // This view renders the tickets page
    }


    //  Display the "Create Ticket" form
    @GetMapping("/new")
    public String showCreateTicketForm(Model model) {
        model.addAttribute("ticket", new TicketEntity());
        return "create_ticket"; // Ensure this matches create_ticket.html
    }

    // Handle form submission for creating a ticket
    @PostMapping
    public String createTicket(@ModelAttribute TicketEntity ticket, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";

        ticket.setUserId(userId); // Set user ID manually
        ticketService.createTicket(ticket.getUserId(), ticket.getTitle(), ticket.getDescription(), ticket.getPriority());
        
        return "redirect:/tickets";
    }
    
    // Show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model)
    {
    	TicketEntity ticket = ticketService.findById(id);
    	if (ticket == null) return "redirect:/tickets"; // handle invalid id
    	
    	model.addAttribute("ticket", ticket);
    	return "edit_ticket"; 
    }
    
    // Handle ticket update
    @PostMapping("/update")
    public String updateTicket(@ModelAttribute TicketEntity ticket,
                               RedirectAttributes redirectAttributes,
                               HttpSession session) {
        String role = (String) session.getAttribute("role");
        if (!"ADMIN".equalsIgnoreCase(role)) {
            // Re-fetch the original ticket and preserve its status
            TicketEntity originalTicket = ticketService.findById(ticket.getId());
            if (originalTicket != null) {
                ticket.setStatus(originalTicket.getStatus());
            }
        }
        ticketService.updateTicket(ticket);
        redirectAttributes.addFlashAttribute("message", "Ticket #" + ticket.getId() + " has been updated.");
        return "redirect:/tickets";
    }

    
    // Handle ticket deletion
    @GetMapping("/delete/{id}")
    public String deleteTicket(@PathVariable Long id, HttpSession session) {
        String role = (String) session.getAttribute("role");
        if (!"ADMIN".equalsIgnoreCase(role)) {
            
            return "redirect:/tickets";
        }
        ticketService.deleteTicket(id);
        return "redirect:/tickets";
    }

    
    
    @GetMapping("/{id}")
    public String viewTicketDetails(@PathVariable Long id, HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login"; // Redirect if not logged in
        }

        String role = (String) session.getAttribute("role");
        TicketEntity ticket;

        // If the user is an admin, allow them to view any ticket
        if ("ADMIN".equalsIgnoreCase(role)) {
            ticket = ticketService.findById(id);
        } else {
            // If not an admin, ensure the ticket belongs to the logged-in user
            ticket = ticketService.findById(id);
            if (ticket == null || !ticket.getUserId().equals(userId)) {
                return "redirect:/tickets"; // Access denied
            }
        }

        model.addAttribute("ticket", ticket);
        return "ticket_details"; 
    }

    

    
    
}
