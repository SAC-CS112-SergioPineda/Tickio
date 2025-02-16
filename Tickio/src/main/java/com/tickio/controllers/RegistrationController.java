package com.tickio.controllers;

import com.tickio.models.User;
import com.tickio.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) 
    {
        model.addAttribute("user", new User());
        return "register"; // Renders register.html
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user,
                           BindingResult result) 
    {
        if (result.hasErrors()) {
            return "register";
        }

        boolean success = userService.registerUser(user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getPhone(), user.getUsername(), user.getPassword());

        if (!success) 
        {
            result.rejectValue("username", "error.user", "Username or email already taken.");
            return "register";
        }

        return "redirect:/login";
    }
}
