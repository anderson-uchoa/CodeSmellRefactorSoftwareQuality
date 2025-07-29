package org.example.studyregistry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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

    // getters e setters mantidos iguais

    public String getTitle() { return title; }
    public void setTitle(String title) {
        this.title = title;
        this.name = title;
    }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    // --- Métodos que encapsulam lógica complexa para evitar data class ---


    public long getAgeInDays() {
        if (date == null) return -1;
        return ChronoUnit.DAYS.between(date, LocalDateTime.now());
    }

    public String getFormattedDate() {
        if (date == null) return "N/A";
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    /**
     * Atualiza título e descrição simultaneamente.
     */
    public void update(String title, String description) {
        setTitle(title);
        setDescription(description);
    }


    public boolean isOverdue(LocalDateTime deadline) {
        if (date == null || deadline == null) return false;
        return date.isAfter(deadline);
    }

    public String getSummary() {
        return String.format("%s by %s on %s", title, author, getFormattedDate());
    }
}