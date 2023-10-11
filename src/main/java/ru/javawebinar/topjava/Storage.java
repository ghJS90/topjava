package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface Storage {
    void addMeal(Meal meal);

    void delete(Integer id);

    void updateMeal(Meal meal);

    Meal getMealByID(Integer id);

    List<Meal> getStorage();
}
