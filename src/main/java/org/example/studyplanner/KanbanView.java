package org.example.studyplanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KanbanView {
    public enum State {
        TODO, DOING, DONE
    }

    HabitTracker habitTracker;
    TodoTracker todoTracker;
    Map<State, List<PlannerMaterial>> kanban;


    public KanbanView(HabitTracker habitTracker, TodoTracker todoTracker) {
        this.habitTracker = habitTracker;
        this.todoTracker = todoTracker;
        this.kanban = new HashMap<>();
        this.kanban.put(State.TODO, new ArrayList<>());
        this.kanban.put(State.DOING, new ArrayList<>());
        this.kanban.put(State.DONE, new ArrayList<>());
    }

    public List<PlannerMaterial> getKanbanByState(State state) {
        return kanban.get(state);
    }

    public void addHabitToKanban(State state, Integer id) throws Exception {
        try {
            Habit toAdd = this.habitTracker.getHabitById(id);
            if (toAdd == null) {
                throw new IllegalArgumentException("Habit not found with id: " + id);
            }
            kanban.get(state).add(toAdd);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void addToDoToKanban(State state, Integer id) throws Exception {
        try {
            ToDo toAdd = this.todoTracker.getToDoById(id);
            if (toAdd == null) {
                throw new IllegalArgumentException("ToDo not found with id: " + id);
            }
            kanban.get(state).add(toAdd);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void removeHabitFromKanban(State state, Integer id) {
        try {
            Habit toRemove = this.habitTracker.getHabitById(id);
            if (toRemove == null) {
                throw new IllegalArgumentException("No habit found with id: " + id);
            }
            kanban.get(state).remove(toRemove);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void removeToDoFromKanban(State state, Integer id) throws Exception {
        try {
            ToDo toRemove = this.todoTracker.getToDoById(id);
            if (toRemove == null) {
                throw new IllegalArgumentException("No todo found with id: " + id);
            }
            kanban.get(state).remove(toRemove);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String kanbanView() {
        if (kanban == null || kanban.isEmpty()) {
            return "No material found";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[ Material ToDo:").append(System.lineSeparator());
        sb.append(formatSection(State.TODO));

        sb.append(System.lineSeparator()).append("Material in progress:")
                .append(System.lineSeparator());
        sb.append(formatSection(State.DOING));

        sb.append(System.lineSeparator()).append("Material completed:")
                .append(System.lineSeparator());
        sb.append(formatSection(State.DONE));

        sb.append("]");

        return sb.toString();
    }

    private String formatSection(State state) {
        List<PlannerMaterial> materials = kanban.get(state);
        if (materials == null || materials.isEmpty()) {
            return "No material found" + System.lineSeparator();
        }

        StringBuilder sb = new StringBuilder();
        for (PlannerMaterial material : materials) {
            sb.append("- ").append(material.toString()).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
