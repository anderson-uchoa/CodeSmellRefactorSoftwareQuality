package org.example.studysearch;

import org.example.studycards.CardManager;
import org.example.studyplanner.HabitTracker;
import org.example.studyplanner.TodoTracker;
import org.example.studyregistry.StudyMaterial;
import org.example.studyregistry.StudyTaskManager;

import java.util.ArrayList;
import java.util.List;

public class GeneralSearch implements Search<String> {
    private SearchLog searchLog = new SearchLog("General Search");

    public GeneralSearch() {}

    @Override
    public List<String> search(String text) {
        return handleSearch(text);
    }

    public SearchLog getSearchLog(){
        return searchLog;
    }

    private List<String> handleSearch(String text) {
        List<String> results = collectAllMatches(text);
        searchLog.recordSearch(text);
        results.add("\nLogged in: " + searchLog.getLogName());
        return results;
    }

    private List<String> collectAllMatches(String text) {
        List<String> results = new ArrayList<>();
        results.addAll(searchInCards(text));
        results.addAll(searchInHabits(text));
        results.addAll(searchInTodos(text));
        results.addAll(searchInMaterials(text));
        results.addAll(searchInTasks(text));
        return results;
    }

    private List<String> searchInCards(String text) {
        return CardManager.getCardManager().searchInCards(text);
    }

    private List<String> searchInHabits(String text) {
        return HabitTracker.getHabitTracker().searchInHabits(text);
    }

    private List<String> searchInTodos(String text) {
        return TodoTracker.getInstance().searchInTodos(text);
    }

    private List<String> searchInMaterials(String text) {
        return StudyMaterial.getStudyMaterial().searchInMaterials(text);
    }

    private List<String> searchInTasks(String text) {
        return StudyTaskManager.getStudyTaskManager().searchInRegistries(text);
    }
}

