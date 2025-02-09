package com.tickio.controllers;

import com.tickio.models.LoginForm;
import com.tickio.services.AuthenticationService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController {
	
	@Autowired
	private AuthenticationService authService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";  // resolves to login.html
    }
    
    @PostMapping("/login")
    public String processLogin(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {
        if (authService.authenticate(loginForm.getUsername(), loginForm.getPassword())) {
            model.addAttribute("message", "Login successful!");
            return "redirect:/home";  // Redirect to home page after successful login
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login"; // Stay on login page with error message
        }
    }
    
}
