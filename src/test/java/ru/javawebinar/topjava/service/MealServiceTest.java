package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-jdbc-app.xml",
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml",
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(ID_1, USER_ID);
        assertMatch(meal, userMeal1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND_ID, USER_ID));
    }

    @Test
    public void getByAnotherUser() {
        assertThrows(NotFoundException.class, () -> service.get(ID_1, ADMIN_ID));
    }

    @Test
    public void delete() {
        service.delete(ID_1, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(ID_1, USER_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND_ID, USER_ID));
    }

    @Test
    public void deleteByAnotherUser() {
        assertThrows(NotFoundException.class, () -> service.get(ID_1, ADMIN_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> actual = service.getBetweenInclusive(
                LocalDate.of(2020, Month.JANUARY, 1),
                LocalDate.of(2020, Month.JANUARY, 2),
                USER_ID);
        List<Meal> expected = Arrays.asList(userMeal4, userMeal3, userMeal2, userMeal1);
        assertMatch(actual, expected);
    }

    @Test
    public void getBetweenNullParam() {
        List<Meal> actual = service.getBetweenInclusive(null, null, USER_ID);
        List<Meal> expected = Arrays.asList(userMeal5, userMeal4, userMeal3, userMeal2, userMeal1);
        assertMatch(actual, expected);
    }

    @Test
    public void getAll() {
        List<Meal> actual = service.getAll(USER_ID);
        List<Meal> expected = Arrays.asList(userMeal5, userMeal4, userMeal3, userMeal2, userMeal1);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void update() {
        Meal updated = getUpdatedMeal();
        service.update(updated, USER_ID);
        assertMatch(service.get(updated.getId(), USER_ID), getUpdatedMeal());
    }

    @Test
    public void updMealByAnotherUser() {
        Meal updated = getUpdatedMeal();
        assertThrows(NotFoundException.class, () -> service.update(updated, ADMIN_ID));
    }

    @Test
    public void create() {
        Meal created = service.create(getNewMeal(), USER_ID);
        Meal newMeal = getNewMeal();
        newMeal.setId(created.getId());
        assertMatch(created, newMeal);
        assertMatch(service.get(newMeal.getId(), USER_ID), newMeal);
    }

    @Test
    public void duplicateDateTimeCreate() {
        Meal newMeal = getNewMeal();
        newMeal.setDateTime(userMeal1.getDateTime());
        assertThrows(DuplicateKeyException.class, () -> service.create(newMeal, USER_ID));
    }
}