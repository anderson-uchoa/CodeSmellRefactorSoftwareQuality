package org.example.controllers;

import org.example.studysearch.GeneralSearch;
import org.example.studysearch.MaterialSearch;
import org.example.studysearch.RegistrySearch;
import org.example.studysearch.SearchLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.controllers.MainController.getInput;
import static org.example.controllers.MainController.validateInput;

public class StudySearchController {
    GeneralSearch generalSearch = new GeneralSearch();
    MaterialSearch materialSearch = new MaterialSearch();
    RegistrySearch registrySearch = new RegistrySearch();

    private Map<String, Runnable> actions = new HashMap<>();

    public StudySearchController() {
        assignActions();
    }

    void assignActions(){
        actions.put("1", this::handleGeneralSearch);
        actions.put("2", this::handleMaterialSearch);
        actions.put("3", this::handleRegistrySearch);
        actions.put("4", this::handleGetGeneralSearchLog);
        actions.put("5", this::handleGetMaterialSearchLog);
        actions.put("6", this::handleGetRegistrySearchLog);
    }

    private void handleMethodHeader(String header){
        System.out.println("???" + header + "!!!\n");
    }

    private void handleGeneralSearch(){
        handleMethodHeader("(General Search)");
        System.out.println("Type search text: ");
        List<String> response = generalSearch.search(getInput());
        if(response.size() == 1){
            response.add("No results found");
        }
        System.out.println(String.join(", ", response));
    }

    private void handleMaterialSearch(){
        handleMethodHeader("(Material Search)");
        System.out.println("Type search text: ");
        List<String> response = materialSearch.search(getInput());
        if(response.size() == 1){
            response.add("No results found");
        }
        System.out.println(String.join(", ", response));
    }

    private void handleRegistrySearch(){
        handleMethodHeader("(Registry Search)");
        System.out.println("Type search text: ");
        List<String> response = registrySearch.search(getInput());
        if(response.size() == 1){
            response.add("No results found");
        }
        System.out.println(String.join(", ", response));
    }

    private void handleGetGeneralSearchLog(){
        handleMethodHeader("(Get Search Log)");
        SearchLog generalLog = generalSearch.getSearchLog();
        String response = generalLog.getLogName() + " was used: " + generalLog.getNumUsages() + " times\nSearch Log\n";
        response += String.join(", ", generalLog.getSearchHistory());
        System.out.println(response);
    }

    private void handleGetMaterialSearchLog(){
        handleMethodHeader("(Get Search Log)");
        SearchLog materialLog = materialSearch.getSearchLog();
        String response = materialLog.getLogName() + " was used: " + materialLog.getNumUsages() + " times\nSearch Log\n";
        response += String.join(", ", materialLog.getSearchHistory());
        System.out.println(response);
    }

    private void handleGetRegistrySearchLog(){
        handleMethodHeader("(Get Search Log)");
        SearchLog registryLog = registrySearch.getSearchLog();
        String response = registryLog.getLogName() + " was used: " + registryLog.getNumUsages() + " times\nSearch Log\n";
        response += String.join(", ", registryLog.getSearchHistory());
        System.out.println(response);
    }

    public void handleSearchInput(){
        try{
            while(true){
                controllerOptions();
                String response = validateInput(actions);
                if(response == null) {return;}
                actions.get(response).run();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void controllerOptions(){
        System.out.println("""
                0 - return
                1 - general search
                2 - material search
                3 - registry search
                4 - get general search log
                5 - get material search log
                6 - get registry search log
               """);
    }
}
