package org.example.studyplanner;

import java.text.MessageFormat;

public class ToDo implements PlannerMaterial {
    private Integer id;
    private String title;
    private String description;
    private int priority;

    public ToDo(Integer id, String title, String description, int priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return MessageFormat.format("[(Priority:{3}) ToDo {0}: {1}, {2}]", id, title, description, priority);
    }

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
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    // ---------- NOVOS MÉTODOS PARA EVITAR DATA CLASS ----------

    public boolean isHighPriority() {
        return priority > 5;
    }


    public String getSummary() {
        return MessageFormat.format("ToDo {0}: Priority {1}", title, priority);
    }


    public void changePriority(int delta) {
        int newPriority = this.priority + delta;
        if (newPriority < 1) newPriority = 1;
        else if (newPriority > 10) newPriority = 10;
        this.priority = newPriority;
    }


    public boolean containsKeyword(String keyword) {
        if (keyword == null) return false;
        String lower = keyword.toLowerCase();
        return (title != null && title.toLowerCase().contains(lower))
                || (description != null && description.toLowerCase().contains(lower));
    }
}