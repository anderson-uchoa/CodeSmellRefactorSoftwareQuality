package org.example.studysearch;

import org.example.studycards.CardManager;
import org.example.studyplanner.HabitTracker;
import org.example.studyplanner.TodoTracker;
import org.example.studyregistry.StudyMaterial;
import org.example.studyregistry.StudyTaskManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchLog {
    private List<String> searchHistory;
    private Map<String, Integer> searchCount;
    private boolean isLocked;
    private Integer numUsages;
    private String logName;

    public SearchLog(String logName) {
        searchHistory = new ArrayList<>();
        searchCount = new HashMap<>();
        this.logName = logName;
        numUsages = 0;
        isLocked = false;
    }
    public void addSearchHistory(String searchHistory) {
        this.searchHistory.add(searchHistory);
    }
    public List<String> getSearchHistory() {
        return searchHistory;
    }
    public void setSearchHistory(List<String> searchHistory) {
        this.searchHistory = searchHistory;
    }
    public Map<String, Integer> getSearchCount() {
        return searchCount;
    }
    public void setSearchCount(Map<String, Integer> searchCount) {
        this.searchCount = searchCount;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public Integer getNumUsages() {
        return numUsages;
    }

    public void setNumUsages(Integer numUsages) {
        this.numUsages = numUsages;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public List<String> performSearch(String text) {
        List<String> results = new ArrayList<>();
        results.addAll(CardManager.getCardManager().searchInCards(text));
        results.addAll(HabitTracker.getHabitTracker().searchInHabits(text));
        results.addAll(TodoTracker.getInstance().searchInTodos(text));
        results.addAll(StudyMaterial.getStudyMaterial().searchInMaterials(text));
        results.addAll(StudyTaskManager.getStudyTaskManager().searchInRegistries(text));

        // Logging behavior
        this.addSearchHistory(text);
        this.setNumUsages(this.getNumUsages() + 1);

        results.add("\nLogged in: " + this.getLogName());
        return results;
    }

    public List<String> logAndWrapResults(String text){
        List<String> results = new ArrayList<>();
        results.addAll(StudyMaterial.getStudyMaterial().searchInMaterials(text));
        this.addSearchHistory(text);
        this.setNumUsages(this.getNumUsages() + 1);
        results.add("\nLogged in: " + this.getLogName());
        return results;
    }

    public List<String> searchAndLog(String text) {
        List<String> results = new ArrayList<>();
        results.addAll(CardManager.getCardManager().searchInCards(text));
        results.addAll(HabitTracker.getHabitTracker().searchInHabits(text));
        results.addAll(TodoTracker.getInstance().searchInTodos(text));
        results.addAll(StudyTaskManager.getStudyTaskManager().searchInRegistries(text));

        this.addSearchHistory(text);
        this.setNumUsages(this.getNumUsages() + 1);
        results.add("\nLogged in: " + this.getLogName());
        return results;
    }
}
