package org.example.studysearch;

import java.util.*;

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
        // Atualiza contagem no mapa de forma automática
        this.searchCount.put(searchHistory, this.searchCount.getOrDefault(searchHistory, 0) + 1);
    }

    public List<String> getSearchHistory() {
        return Collections.unmodifiableList(searchHistory);
    }

    public void setSearchHistory(List<String> searchHistory) {
        this.searchHistory = new ArrayList<>(searchHistory);
        // Atualiza o mapa também ao trocar a lista inteira
        this.searchCount.clear();
        for (String s : searchHistory) {
            this.searchCount.put(s, this.searchCount.getOrDefault(s, 0) + 1);
        }
    }

    public Map<String, Integer> getSearchCount() {
        return Collections.unmodifiableMap(searchCount);
    }

    public void setSearchCount(Map<String, Integer> searchCount) {
        this.searchCount = new HashMap<>(searchCount);
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

    // --- NOVOS MÉTODOS PARA EVITAR DATA CLASS ---

    public String getMostFrequentSearch() {
        return searchCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }


    public int getUniqueSearchCount() {
        return searchCount.size();
    }

    public void incrementNumUsages() {
        this.numUsages = this.numUsages + 1;
    }


    public void clearHistory() {
        this.searchHistory.clear();
        this.searchCount.clear();
        this.numUsages = 0;
    }
}