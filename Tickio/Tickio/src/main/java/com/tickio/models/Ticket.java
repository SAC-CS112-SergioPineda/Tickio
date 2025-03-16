package com.tickio.models;

import java.time.LocalDateTime;

/**
 * Represents a ticket in the system.
 * A ticket contains details such as title, description, status, priority, and submission date.
 */
public class Ticket {

    /** The unique identifier of the ticket. */
    private Long id;

    /** The title of the ticket. */
    private String title;

    /** A detailed description of the ticket. */
    private String description;

    /** The current status of the ticket (e.g., Open, In Progress, Closed). */
    private String status;

    /** The priority level of the ticket (e.g., Low, Medium, High). */
    private String priority;

    /** The date and time when the ticket was submitted. */
    private LocalDateTime dateSubmitted;

    /** A formatted version of the submission date for display purposes. */
    private String formattedDate;

    /**
     * Default constructor.
     * Initializes the ticket with the current date and sets the default status to "Open".
     */
    public Ticket() {
        this.dateSubmitted = LocalDateTime.now(); // Default to current time
        this.status = "Open";  // Default status
    }

    /**
     * Gets the unique identifier of the ticket.
     *
     * @return The ticket ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the ticket.
     *
     * @param id The ticket ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the title of the ticket.
     *
     * @return The title of the ticket.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the ticket.
     *
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description of the ticket.
     *
     * @return The description of the ticket.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the ticket.
     *
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the current status of the ticket.
     *
     * @return The status of the ticket.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the ticket.
     *
     * @param status The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the priority level of the ticket.
     *
     * @return The priority of the ticket.
     */
    public String getPriority() {
        return priority;
    }

    /**
     * Sets the priority level of the ticket.
     *
     * @param priority The priority to set.
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * Gets the date and time when the ticket was submitted.
     *
     * @return The submission date and time.
     */
    public LocalDateTime getDateSubmitted() {
        return dateSubmitted;
    }

    /**
     * Sets the date and time when the ticket was submitted.
     *
     * @param dateSubmitted The submission date and time to set.
     */
    public void setDateSubmitted(LocalDateTime dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    /**
     * Gets the formatted date string of the ticket submission.
     *
     * @return The formatted submission date.
     */
    public String getFormattedDate() {
        return formattedDate;
    }

    /**
     * Sets the formatted date string of the ticket submission.
     *
     * @param formattedDate The formatted date to set.
     */
    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }
}
