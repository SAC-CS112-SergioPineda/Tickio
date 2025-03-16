package com.tickio.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;

@Table("TICKETS") // Database table name
public class TicketEntity {

    @Id
    private Long id;

    @Column("USER_ID") // Link to the user who created the ticket
    private Long userId;
    
    @Transient // manually fetch UserEntity
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

    public TicketEntity() {
        this.dateSubmitted = LocalDateTime.now();
        this.status = "Open"; // Default status
    }

    public TicketEntity(Long userId, String title, String description, String priority) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = "Open";
        this.dateSubmitted = LocalDateTime.now();
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getDateSubmitted() { return dateSubmitted; }
    public void setDateSubmitted(LocalDateTime dateSubmitted) { this.dateSubmitted = dateSubmitted; }
}
