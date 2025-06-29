package org.example.studysearch;

import org.example.studycards.CardManager;
import org.example.studyplanner.HabitTracker;
import org.example.studyplanner.TodoTracker;
import org.example.studyregistry.StudyTaskManager;

import java.util.ArrayList;
import java.util.List;

public class RegistrySearch implements Search<String>{
    private SearchLog searchLog = new SearchLog("Registry Search");
    public RegistrySearch(){}

    @Override
    public List<String> search(String text) {
        return handleRegistrySearch(text);
    }

    public SearchLog getSearchLog() {
        return searchLog;
    }

    private List<String> handleRegistrySearch(String text) {
        // 1) collect all the raw results
        List<String> results = collectRegistryResults(text);
        // 2) record/log the search and append the formatted entry
        results.add(logAndFormatEntry(text));
        return results;
    }

    private List<String> collectRegistryResults(String text) {
        List<String> results = new ArrayList<>();
        results.addAll(CardManager.getCardManager().searchInCards(text));
        results.addAll(HabitTracker.getHabitTracker().searchInHabits(text));
        results.addAll(TodoTracker.getInstance().searchInTodos(text));
        results.addAll(StudyTaskManager.getStudyTaskManager().searchInRegistries(text));
        return results;
    }

    private String logAndFormatEntry(String text) {
        // SearchLog.recordAndFormatEntry adds history, bumps usage count,
        // and returns the formatted “\nLogged in: …” string.
        return searchLog.recordAndFormatEntry(text);
    }
}
