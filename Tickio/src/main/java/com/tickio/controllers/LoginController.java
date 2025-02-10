package com.tickio.controllers;

import com.tickio.models.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login"; // Renders login.html
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginForm") LoginForm loginForm,
                        BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            return "login";
        }

        // Hardcoded credentials (for now)
        if ("admin".equals(loginForm.getUsername()) && "password".equals(loginForm.getPassword())) {
            session.setAttribute("user", "admin"); // Store user in session
            return "redirect:/dashboard"; // Redirect to user dashboard
        }

        result.rejectValue("password", "error.loginForm", "Invalid username or password.");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Clear session
        return "redirect:/"; // Redirect to login page
    }
}
