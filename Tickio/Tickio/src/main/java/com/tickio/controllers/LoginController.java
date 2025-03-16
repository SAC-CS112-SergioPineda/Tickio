package com.tickio.controllers;

import com.tickio.models.LoginForm;
import com.tickio.services.UserService;
import com.tickio.data.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

/**
 * Controller for handling user authentication, including login and logout.
 */
@Controller
public class LoginController {

    private final UserService userService;

    /**
     * Constructs a LoginController with the specified UserService.
     *
     * @param userService The service used to manage user authentication.
     */
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Displays the login form.
     *
     * @param model The model to hold form data.
     * @return The name of the login view (login.html).
     */
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    /**
     * Handles login form submission and authentication.
     *
     * @param loginForm The login form containing user credentials.
     * @param result The result of form validation.
     * @param session The HTTP session to store user data upon successful login.
     * @param model The model to hold error messages if login fails.
     * @return Redirects to the dashboard on success, or reloads the login page on failure.
     */
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginForm") LoginForm loginForm,
                        BindingResult result, HttpSession session, Model model) {
        if (result.hasErrors()) {
            return "login";
        }

        Optional<UserEntity> user = userService.getUserByUsername(loginForm.getUsername());

        if (user.isPresent() && user.get().getPassword().equals(loginForm.getPassword())) {
            session.setAttribute("userId", user.get().getId());
            session.setAttribute("username", user.get().getUsername());
            session.setAttribute("role", user.get().getRole());  // Save role in session
            return "redirect:/dashboard";
        }

        model.addAttribute("error", "Invalid username or password.");
        return "login";
    }

    /**
     * Handles user logout by invalidating the session.
     *
     * @param session The HTTP session to be invalidated.
     * @return Redirects to the home page after logout.
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
