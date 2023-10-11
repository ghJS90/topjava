package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class MealTo {
    private int id;
    private final LocalDateTime dateTime;
    private final String description;
    private final int calories;

    private final boolean excess;

    public MealTo(Integer id, LocalDateTime dateTime, String description, int calories, boolean excess) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isExcess() {
        return excess;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + excess +
                '}';
    }
}
