package org.example.studyplanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KanbanView {
    public enum State{
        TODO, DOING, DONE;
    }

    HabitTracker habitTracker = null;
    TodoTracker todoTracker = null;
    Map<State, List<PlannerMaterial>> kanban = null;

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
        try{
            Habit toAdd = this.habitTracker.getHabitById(id);
            if(toAdd == null){
                throw new IllegalArgumentException("Habit not found with id: " + id);
            }
            kanban.get(state).add(toAdd);
        } catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void addToDoToKanban(State state, Integer id) throws Exception {
        try{
            ToDo toAdd = this.todoTracker.getToDoById(id);
            if(toAdd == null){
                throw new IllegalArgumentException("ToDo not found with id: " + id);
            }
            kanban.get(state).add(toAdd);
        } catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void removeHabitFromKanban(State state, Integer id) throws Exception {
        try{
            Habit toRemove = this.habitTracker.getHabitById(id);
            if(toRemove == null) {
                throw new IllegalArgumentException("No habit found with id: " + id);
            }
            kanban.get(state).remove(toRemove);
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    public void removeToDoFromKanban(State state, Integer id) throws Exception {
        try{
            ToDo toRemove = this.todoTracker.getToDoById(id);
            if(toRemove == null) {
                throw new IllegalArgumentException("No todo found with id: " + id);
            }
            kanban.get(state).remove(toRemove);
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public String kanbanView() throws Exception {
        try{

            if(kanban.isEmpty()){
                throw new Exception("No material found");
            }
            StringBuilder sb = new StringBuilder();
            sb.append("[ Material ToDo: ");
            sb.append(System.lineSeparator());

            if(kanban.get(State.TODO).isEmpty()){
                sb.append("No material found");
            } else {
                for(PlannerMaterial material : kanban.get(State.TODO)){
                    sb.append(", ").append(material.toString());
                }
            }
            sb.append(System.lineSeparator());
            sb.append("Material in progress:");
            sb.append(System.lineSeparator());
            if(kanban.get(State.DOING).isEmpty()){
                sb.append("No material found");
            } else {
                for(PlannerMaterial material : kanban.get(State.DOING)){
                    sb.append(", ").append(material.toString());
                }
            }
            sb.append(System.lineSeparator());
            sb.append("Material completed:");
            sb.append(System.lineSeparator());
            if(kanban.get(State.DONE).isEmpty()){
                sb.append("No material found");
            } else {
                for(PlannerMaterial material : kanban.get(State.DONE)){
                    sb.append(", ").append(material.toString());
                }
            }
            sb.append("]");
            return sb.toString();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
