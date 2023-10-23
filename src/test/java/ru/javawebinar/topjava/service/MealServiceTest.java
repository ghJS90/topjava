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

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(id, USER_ID);
        assertThat(user_meal_1).isEqualTo(meal);
    }

    @Test(expected = NotFoundException.class)
    public void getByAnotherUser() {
        service.get(id, ANOTHER_USER);
    }

    @Test
    public void delete() {
        service.delete(id, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(id, USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void deleteByAnotherUser() {
        service.delete(id, ANOTHER_USER);
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> expected = service.getBetweenInclusive(
                service.get(id, USER_ID).getDateTime().toLocalDate(),
                service.get(id, USER_ID).getDateTime().toLocalDate(),
                USER_ID);
        assertThat(expected).isEqualTo(Arrays.asList(user_meal_2, user_meal_1));
    }

    @Test
    public void getAll() {
        List<Meal> expected = service.getAll(USER_ID);
        List<Meal> actual = Arrays.asList(user_meal_2, user_meal_1);
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, USER_ID);
        assertThat(service.get(updated.getId(), USER_ID)).isEqualTo(updated);
    }

    @Test(expected = NotFoundException.class)
    public void updMealByAnotherUser() {
        Meal updated = getUpdated();
        service.update(updated, ANOTHER_USER);
    }

    @Test
    public void create() {
        Meal newMeal = new Meal(getNew());
        service.create(newMeal, USER_ID);
        assertThat(newMeal).isEqualTo(service.get(newMeal.getId(), USER_ID));
    }

    @Test(expected = DuplicateKeyException.class)
    public void duplicateDateTimeCreate() {
        Meal newMeal = new Meal(getNew());
        newMeal.setDateTime(service.get(id, USER_ID).getDateTime());
        service.create(newMeal, USER_ID);
    }
}