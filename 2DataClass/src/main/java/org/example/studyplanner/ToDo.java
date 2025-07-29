package org.example.studyplanner;

import java.text.MessageFormat;

public class ToDo implements PlannerMaterial {
    private Integer id;
    private String title;
    private String description;
    private int priority; // 1 (highest) to 5 (lowest)

    public ToDo(Integer id, String title, String description, int priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        setPriority(priority); // use setter to enforce validation
    }

    // Behavior: Check if the task is urgent
    public boolean isUrgent() {
        return this.priority <= 2; // priority 1 or 2 is urgent
    }

    // Behavior: Get a summary of the task
    public String getSummary() {
        return title + ": " + (description.length() > 50 ? description.substring(0, 47) + "..." : description);
    }

    // Behavior: Update task content safely
    public void updateDetails(String newTitle, String newDescription) {
        if (newTitle != null && !newTitle.isBlank()) {
            this.title = newTitle;
        }
        if (newDescription != null && !newDescription.isBlank()) {
            this.description = newDescription;
        }
    }

    @Override
    public String toString() {
        return MessageFormat.format("[(Priority:{3}) ToDo {0}: {1}, {2}]", id, title, description, priority);
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title != null && !title.isBlank()) {
            this.title = title;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null && !description.isBlank()) {
            this.description = description;
        }
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if (priority < 1 || priority > 5) {
            throw new IllegalArgumentException("Priority must be between 1 (high) and 5 (low).");
        }
        this.priority = priority;
    }
}

