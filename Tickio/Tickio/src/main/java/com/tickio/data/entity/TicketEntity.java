package com.tickio.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;

/**
 * Represents a ticket entity in the system.
 * This class is mapped to the "TICKETS" table in the database.
 */
@Table("TICKETS")
public class TicketEntity {

    @Id
    private Long id;

    @Column("USER_ID")
    private Long userId;
    
    @Transient
    private UserEntity user;

    @Column("TITLE")
    private String title;

    @Column("DESCRIPTION")
    private String description;

    @Column("PRIORITY")
    private String priority;

    @Column("STATUS")
    private String status;

    @Column("DATE_SUBMITTED")
    private LocalDateTime dateSubmitted;

    /**
     * Default constructor that initializes the ticket with the current date and an "Open" status.
     */
    public TicketEntity() {
        this.dateSubmitted = LocalDateTime.now();
        this.status = "Open";
    }

    /**
     * Constructs a TicketEntity with the specified attributes.
     * 
     * @param userId      The ID of the user who created the ticket.
     * @param title       The title of the ticket.
     * @param description The detailed description of the issue or request.
     * @param priority    The priority level of the ticket (e.g., Low, Medium, High).
     */
    public TicketEntity(Long userId, String title, String description, String priority) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = "Open";
        this.dateSubmitted = LocalDateTime.now();
    }

    /**
     * Gets the ticket ID.
     * 
     * @return The unique identifier of the ticket.
     */
    public Long getId() { return id; }

    /**
     * Sets the ticket ID.
     * 
     * @param id The unique identifier of the ticket.
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Gets the user ID associated with this ticket.
     * 
     * @return The ID of the user who created the ticket.
     */
    public Long getUserId() { return userId; }

    /**
     * Sets the user ID associated with this ticket.
     * 
     * @param userId The ID of the user who created the ticket.
     */
    public void setUserId(Long userId) { this.userId = userId; }

    /**
     * Gets the title of the ticket.
     * 
     * @return The title of the ticket.
     */
    public String getTitle() { return title; }

    /**
     * Sets the title of the ticket.
     * 
     * @param title The title of the ticket.
     */
    public void setTitle(String title) { this.title = title; }

    /**
     * Gets the description of the ticket.
     * 
     * @return The detailed description of the ticket.
     */
    public String getDescription() { return description; }

    /**
     * Sets the description of the ticket.
     * 
     * @param description The detailed description of the ticket.
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Gets the priority level of the ticket.
     * 
     * @return The priority level (e.g., Low, Medium, High).
     */
    public String getPriority() { return priority; }

    /**
     * Sets the priority level of the ticket.
     * 
     * @param priority The priority level (e.g., Low, Medium, High).
     */
    public void setPriority(String priority) { this.priority = priority; }

    /**
     * Gets the status of the ticket.
     * 
     * @return The status of the ticket (e.g., Open, In Progress, Closed).
     */
    public String getStatus() { return status; }

    /**
     * Sets the status of the ticket.
     * 
     * @param status The status of the ticket (e.g., Open, In Progress, Closed).
     */
    public void setStatus(String status) { this.status = status; }

    /**
     * Gets the date and time when the ticket was submitted.
     * 
     * @return The date and time of submission.
     */
    public LocalDateTime getDateSubmitted() { return dateSubmitted; }

    /**
     * Sets the date and time when the ticket was submitted.
     * 
     * @param dateSubmitted The date and time of submission.
     */
    public void setDateSubmitted(LocalDateTime dateSubmitted) { this.dateSubmitted = dateSubmitted; }
}
