package org.example.studysearch;

import java.util.List;

public class GeneralSearch implements Search<String> {
    private final SearchLog searchLog = new SearchLog("General Search");

    public GeneralSearch() {}

    @Override
    public List<String> search(String text) {
        return searchLog.performSearch(text);
    }

    public SearchLog getSearchLog() {
        return searchLog;
    }
}



