package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal save(Meal meal, int userId) {
        try {
            return repository.save(meal, userId);
        } catch (Exception e) {
            throw new NotFoundException("exception");
        }
    }

    public boolean delete(int id, int uderId) {
        try {
            return repository.delete(id, uderId);
        } catch (Exception e) {
            throw new NotFoundException("exception");
        }
    }

    public Meal get(int id, int userId) {
        try {
            return repository.get(id, userId);
        } catch (Exception e) {
            throw new NotFoundException("exception");
        }
    }

    public List<Meal> getAll(int userId) {
        try {
            return repository.getAll(userId);
        } catch (Exception e) {
            throw new NotFoundException("exception");
        }
    }
}