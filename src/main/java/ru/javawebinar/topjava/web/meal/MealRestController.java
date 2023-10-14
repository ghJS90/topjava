package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public Meal save(Meal meal, int userId) {
        return service.save(meal, userId);
    }

    public boolean delete(int id, int userId) {
        return service.delete(id, userId);
    }

    public Meal get(int id, int userId){
        return service.get(id, userId);
    }

    public List<Meal> getAll(int userId) {
        log.info("getAll");
        return service.getAll(userId);
    }
}