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

    private ToDo(ToDoBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.priority = builder.priority;
    }

    @Override
    public String toString() {
        return MessageFormat.format("[(Priority:{3}) ToDo {0}: {1}, {2}]", id, title, description, priority);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {  // ✅ ADICIONADO
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {  // ✅ OPCIONAL
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {  // ✅ OPCIONAL
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {  // ✅ OPCIONAL
        this.priority = priority;
    }

    // 🔨 Builder Pattern
    public static class ToDoBuilder {
        private Integer id;
        private String title;
        private String description;
        private int priority;

        public ToDoBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public ToDoBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public ToDoBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ToDoBuilder setPriority(int priority) {
            this.priority = priority;
            return this;
        }

        public ToDo build() {
            return new ToDo(this);
        }
    }
}
