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
    private final List<String> searchHistory;
    private final Map<String, Integer> searchCount;
    private boolean isLocked;
    private int numUsages;
    private String logName;

    public SearchLog(String logName) {
        this.searchHistory = new ArrayList<>();
        this.searchCount = new HashMap<>();
        this.logName = logName;
        this.numUsages = 0;
        this.isLocked = false;
    }

    // Adds a search entry and updates usage and count
    public void addSearchHistory(String searchTerm) {
        if (isLocked) {
            throw new IllegalStateException("SearchLog is locked. Cannot record new search.");
        }
        searchHistory.add(searchTerm);
        searchCount.put(searchTerm, searchCount.getOrDefault(searchTerm, 0) + 1);
        incrementUsage();
    }

    // Increments usage counter
    private void incrementUsage() {
        numUsages++;
    }

    // Clears search history and count
    public void clearSearchHistory() {
        searchHistory.clear();
        searchCount.clear();
    }

    // Locks the log
    public void lock() {
        isLocked = true;
    }

    // Unlocks the log
    public void unlock() {
        isLocked = false;
    }

    // Accessors
    public List<String> getSearchHistory() {
        return new ArrayList<>(searchHistory); // return copy to preserve encapsulation
    }

    public Map<String, Integer> getSearchCount() {
        return new HashMap<>(searchCount); // return copy to preserve encapsulation
    }

    public boolean isLocked() {
        return isLocked;
    }

    public int getNumUsages() {
        return numUsages;
    }

    public String getLogName() {
        return logName;
    }

    public void renameLog(String newName) {
        this.logName = newName;
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

        results.add("\nLogged in: " + this.getLogName());
        return results;
    }

    public List<String> logAndWrapResults(String text){
        List<String> results = new ArrayList<>();
        results.addAll(StudyMaterial.getStudyMaterial().searchInMaterials(text));
        this.addSearchHistory(text);
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
        results.add("\nLogged in: " + this.getLogName());
        return results;
    }
}
