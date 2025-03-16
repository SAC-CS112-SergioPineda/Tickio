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

/**
 * Controller for handling user registration.
 */
@Controller
public class RegistrationController {

    private final UserService userService;

    /**
     * Constructs a RegistrationController with the specified UserService.
     *
     * @param userService The service used to handle user registration.
     */
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Displays the registration form.
     *
     * @param model The model to hold the user form data.
     * @return The name of the registration view (register.html).
     */
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    /**
     * Handles user registration form submission.
     *
     * @param user The user object containing registration details.
     * @param result The result of form validation.
     * @return Redirects to the login page on success, or reloads the registration page on failure.
     */
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user,
                           BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }

        boolean success = userService.registerUser(user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getPhone(), user.getUsername(), user.getPassword());

        if (!success) {
            result.rejectValue("username", "error.user", "Username or email already taken.");
            return "register";
        }

        return "redirect:/login";
    }
}
