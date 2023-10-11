package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LocalMealStorage implements MealStorage {
    private final AtomicInteger counter = new AtomicInteger(1);
    public final List<Meal> meals = new ArrayList<>();

    @Override
    public void add(Meal meal) {
        meal.setId(counter.getAndIncrement());
        meals.add(meal);
    }

    @Override
    public void delete(Integer id) {
        meals.removeIf(m -> m.getId().equals(id));
    }

    @Override
    public void update(Meal meal) {
        for (Meal m : meals) {
            if (m.getId().equals(meal.getId())) {
                m.setDescription(meal.getDescription());
                m.setDateTime(meal.getDateTime());
                m.setCalories(meal.getCalories());
                return;
            }
        }
    }

    @Override
    public Meal getByID(Integer id) {
        for (Meal m : meals) {
            if (m.getId().equals(id)) return m;
        }
        return null;
    }

    @Override
    public List<Meal> getAll() {
        return meals;
    }
}
