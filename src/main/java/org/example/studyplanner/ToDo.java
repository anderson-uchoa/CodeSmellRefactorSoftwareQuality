package org.example.studyplanner;

import java.text.MessageFormat;

public class ToDo implements PlannerMaterial {
    private Integer id;
    private String title;
    private String description;
    private PriorityBehavior priorityBehavior;

    public ToDo(Integer id, String title, String description, int priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priorityBehavior = PriorityFactory.create(priority);
    }

    @Override
    public String toString() {
        return MessageFormat.format(
                "[(Priority:{0}) ToDo {1}: {2}, {3} - {4}]",
                getPriority(), id, title, description, getPriorityLabel()
        );
    }

    public Integer getId() {
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
        return priorityBehavior.getValue();
    }

    public void setPriority(int priority) {
        this.priorityBehavior = PriorityFactory.create(priority);
    }

    public String getPriorityLabel() {
        return priorityBehavior.getLabel();
    }

    // Interface para o comportamento de prioridade (Strategy)
    public interface PriorityBehavior {
        int getValue();
        String getLabel();
    }

    public static class HighPriority implements PriorityBehavior {
        public int getValue() { return 1; }
        public String getLabel() { return "Alta"; }
    }

    public static class MediumPriority implements PriorityBehavior {
        public int getValue() { return 2; }
        public String getLabel() { return "Média"; }
    }

    public static class LowPriority implements PriorityBehavior {
        public int getValue() { return 3; }
        public String getLabel() { return "Baixa"; }
    }

    public static class UnknownPriority implements PriorityBehavior {
        public int getValue() { return 0; }
        public String getLabel() { return "Desconhecida"; }
    }

    // Factory para criar as prioridades
    public static class PriorityFactory {
        public static PriorityBehavior create(int priority) {
            return switch (priority) {
                case 1 -> new HighPriority();
                case 2 -> new MediumPriority();
                case 3 -> new LowPriority();
                default -> new UnknownPriority();
            };
        }
    }
}
