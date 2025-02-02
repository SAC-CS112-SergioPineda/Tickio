package com.tickio.controllers;

import com.tickio.models.LoginForm;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController {

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
        // Emulated authentication logic (no database)
        if ("user".equals(loginForm.getUsername()) && "password".equals(loginForm.getPassword())) {
            model.addAttribute("username", loginForm.getUsername());
            return "index"; // once "logged in", return to home page (or product page)
        } else {
            bindingResult.reject("login.failed", "Invalid username or password.");
            return "login";
        }
    }
}
