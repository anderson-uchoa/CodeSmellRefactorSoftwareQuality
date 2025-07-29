package org.example.studyregistry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

public class Task extends Registry {
    private String title;
    private String description;
    private String author;
    private LocalDateTime date;

    public Task(String title, String description, String author, LocalDateTime date) {
        this.title = title;
        this.name = title;
        this.description = description;
        this.author = author;
        this.date = date;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.name = title; // Ensure Registry.name stays in sync
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    // === Added Behavior Methods ===

    /** Check if the task is overdue */
    public boolean isOverdue() {
        return LocalDateTime.now().isAfter(date);
    }

    /** Get a human-readable summary of the task */
    public String getSummary() {
        return String.format("Task: %s\nAuthor: %s\nDue: %s\nDescription: %s",
                title, author, formatDate(), description);
    }

    /** Format the task’s due date for display */
    public String formatDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return date.format(formatter);
    }

    /** Postpone the task by a number of hours */
    public void postponeByHours(long hours) {
        this.date = this.date.plusHours(hours);
    }

    /** Check if the task is due within the next specified duration */
    public boolean isDueWithin(Duration duration) {
        return LocalDateTime.now().plus(duration).isAfter(date);
    }
}

