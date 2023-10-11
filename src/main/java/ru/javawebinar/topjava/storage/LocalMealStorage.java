package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LocalMealStorage implements MealStorage {
    private final AtomicInteger counter = new AtomicInteger(1);
    public final Map<Integer, Meal> mapMeals = new HashMap<>();

    @Override
    public Meal add(Meal meal) {
        Integer key = counter.getAndIncrement();
        meal.setId(key);
        mapMeals.put(key, meal);
        return meal;
    }

    @Override
    public void delete(Integer id) {
        mapMeals.remove(id);
    }

    @Override
    public Meal update(Meal meal) {
        Integer key = meal.getId();
        if (mapMeals.containsKey(meal.getId())) {
            mapMeals.get(key).setDescription(meal.getDescription());
            mapMeals.get(key).setCalories(meal.getCalories());
            mapMeals.get(key).setDateTime(meal.getDateTime());
        }
        return mapMeals.get(key);
    }

    @Override
    public Meal getByID(Integer id) {
        return mapMeals.get(id);
    }

    @Override
    public List<Meal> getAll() {

        return new ArrayList<>(mapMeals.values());
    }
}
