package com.tickio.controllers;

import com.tickio.models.User;
import com.tickio.services.AuthenticationService;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class RegistrationController {

    private final AuthenticationService authService;

    public RegistrationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";  // resolves to register.html
    }

    @PostMapping("/register")
    public String processRegistration(@Valid @ModelAttribute("user") User user,
                                      BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        // Store new user in AuthenticationService
        authService.registerUser(user.getUsername(), user.getPassword());
        model.addAttribute("message", "Registration successful! Please log in.");
        return "redirect:/login"; // Redirect to login page after registration
    }
}
