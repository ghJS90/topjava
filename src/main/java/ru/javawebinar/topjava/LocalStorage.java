package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LocalStorage implements Storage {
    private static final AtomicInteger COUNTER = new AtomicInteger(1);
    public static List<Meal> meals = new ArrayList<>();

    @Override
    public void addMeal(Meal meal) {
        meal.setId(COUNTER.getAndIncrement());
        meals.add(meal);
    }

    @Override
    public void delete(Integer id) {
        meals.removeIf(m -> m.getId().equals(id));
    }

    @Override
    public void updateMeal(Meal meal) {
        for (Meal m : meals) {
            if (m.getId().equals(meal.getId())) {
                m.setDescription(meal.getDescription());
                m.setDateTime(meal.getDateTime());
                m.setCalories(meal.getCalories());
            }
        }
    }

    @Override
    public Meal getMealByID(Integer id) {
        for (Meal m : meals) {
            if (m.getId().equals(id)) return m;
        }
        return null;
    }

    @Override
    public List<Meal> getStorage() {
        return LocalStorage.meals;
    }
}
