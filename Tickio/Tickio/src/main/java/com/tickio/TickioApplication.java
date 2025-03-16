package com.tickio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the Tickio application.
 * This class bootstraps the Spring Boot application using {@link SpringApplication#run(Class, String...)}.
 * The {@code @SpringBootApplication} annotation enables component scanning, auto-configuration, 
 * and Spring Boot application setup.
 * 
 * @author Daniel and Sergio
 * @version 1.0
 * @since 2024-01
 */
@SpringBootApplication
public class TickioApplication {

    /**
     * The main method that starts the Tickio application.
     * <p>
     * This method delegates to {@link SpringApplication#run(Class, String...)} to launch the application.
     * </p>
     *
     * @param args Command-line arguments passed during application startup.
     */
    public static void main(String[] args) {
        SpringApplication.run(TickioApplication.class, args);
    }
}
