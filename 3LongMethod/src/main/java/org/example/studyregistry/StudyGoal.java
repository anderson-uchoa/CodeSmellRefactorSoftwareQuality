package org.example.studyregistry;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StudyGoal extends Registry{
    private String goal;
    private List<String> goalRequirements;
    private Boolean isCompleted;
    private LocalDateTime createdDate;
    private Double goalCompletion;
    private StudyObjective studyObjective;
    private StudyPlan studyPlan;
    private String summary;

    public StudyGoal(String name, StudyObjective objective, StudyPlan plan) {
        this.name = name;
        this.studyObjective = objective;
        this.studyPlan = plan;
        goalRequirements = new ArrayList<>();
    }

    public void editActiveCompleted(boolean active, boolean completed){
        this.isActive = active;
        this.isCompleted = completed;
    }

    public String setGoalSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Goal Summary:\n\n");

        appendActiveGoal(summary);
        appendCompletedGoal(summary);
        appendRequirements(summary);
        appendStudyPlan(summary);
        appendStudyObjective(summary);

        this.summary = summary.toString();
        return summary.toString();
    }

    private void appendActiveGoal(StringBuilder summary) {
        if (this.isActive) {
            summary.append("Active Goal:\n")
                    .append(goal)
                    .append("\n\n");
        }
    }

    private void appendCompletedGoal(StringBuilder summary) {
        if (this.isCompleted) {
            summary.append("Completed Goal:\n")
                    .append(goal)
                    .append("\n\n");
        }
    }

    private void appendRequirements(StringBuilder summary) {
        if (this.goalRequirements != null && !goalRequirements.isEmpty()) {
            summary.append("Requirements:\n");
            for (String requirement : goalRequirements) {
                summary.append(requirement).append(", ");
            }
            summary.setLength(summary.length() - 2); // Remove trailing comma and space
            summary.append("\n\n");
        }
    }

    private void appendStudyPlan(StringBuilder summary) {
        if (this.studyPlan != null) {
            summary.append("Plan:\n")
                    .append(this.studyPlan.toString())
                    .append("\n\n");
        }
    }

    private void appendStudyObjective(StringBuilder summary) {
        if (this.studyObjective != null) {
            summary.append("Objective:\n")
                    .append(this.studyObjective.toString())
                    .append("\n\n");
        }
    }


    public void addRequirement(String requirement){
        this.goalRequirements.add(requirement);
    }

    public void resetRequirements(){
        this.goalRequirements.clear();
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void toggleIsCompleted(){
        this.isCompleted = !this.isCompleted;
    }

    public LocalDateTime getLimitDate() {
        return createdDate;
    }

    public void setLimitDate(LocalDateTime limitDate) {
        this.createdDate = limitDate;
    }

    public void addDaysLimitDate(int days){
        this.createdDate = this.createdDate.plusDays(days);
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }
}
