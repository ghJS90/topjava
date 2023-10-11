package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealStorage {
    void add(Meal meal);

    void delete(Integer id);

    void update(Meal meal);

    Meal getByID(Integer id);

    List<Meal> getAll();
}
