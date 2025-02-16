package com.tickio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/login"; // Redirects if user is not logged in
        }

        model.addAttribute("username", session.getAttribute("username")); // Add username to model
        return "dashboard"; // Loads dashboard.html
    }
}
