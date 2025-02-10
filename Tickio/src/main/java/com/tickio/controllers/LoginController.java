package com.tickio.controllers;


import com.tickio.models.LoginForm;
import com.tickio.services.AuthenticationService;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController {

    private final AuthenticationService authService;

    public LoginController(AuthenticationService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";  // resolves to login.html
    }

    @PostMapping("/login")
    public String processLogin(@Valid @ModelAttribute("loginForm") LoginForm loginForm,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "login";
        }

        // Use AuthenticationService instead of hardcoded logic
        if (authService.authenticate(loginForm.getUsername(), loginForm.getPassword())) {
            model.addAttribute("username", loginForm.getUsername());
            return "dashboard"; // Redirect to dashboard after successful login
        } else {
            bindingResult.reject("login.failed", "Invalid username or password.");
            return "login";
        }
    }
}

