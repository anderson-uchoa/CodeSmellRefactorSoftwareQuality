package org.example.studyplanner;

public record HabitCreationData(String name, String motivation, Integer dailyMinutesDedication,
                                Integer dailyHoursDedication, Integer year, Integer month, Integer day, Integer hour,
                                Integer minute, Integer seconds, Boolean isConcluded) {
}