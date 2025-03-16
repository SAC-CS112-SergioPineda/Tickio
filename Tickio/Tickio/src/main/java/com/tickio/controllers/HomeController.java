package com.tickio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for handling the home page requests.
 */
@Controller
public class HomeController {

    /**
     * Handles requests to the root URL ("/") and returns the home page.
     *
     * @return The name of the view that resolves to index.html.
     */
    @GetMapping("/")
    public String home() {
        return "index";  // Resolves to index.html
    }
}
