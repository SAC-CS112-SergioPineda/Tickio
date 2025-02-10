package com.tickio.models;

import java.time.LocalDateTime;

public class Ticket {
    private Long id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private LocalDateTime dateSubmitted;
    
    private String formattedDate;

    public Ticket() {
        this.dateSubmitted = LocalDateTime.now(); // Default to current time
        this.status = "Open";  // Default status
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public LocalDateTime getDateSubmitted() { return dateSubmitted; }
    public void setDateSubmitted(LocalDateTime dateSubmitted) { this.dateSubmitted = dateSubmitted; }
    
 
    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }
}
