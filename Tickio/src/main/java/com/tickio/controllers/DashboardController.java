package com.tickio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login"; // Redirect to login if user is not logged in
        }
        model.addAttribute("username", session.getAttribute("user")); // Pass username to view
        return "dashboard";
    }
}

